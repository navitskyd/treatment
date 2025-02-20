package com.ai.omed.treatment.service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ai.omed.treatment.model.TreatmentPlan;
import com.ai.omed.treatment.model.TreatmentPlanStatus;
import com.ai.omed.treatment.model.TreatmentTask;
import com.ai.omed.treatment.model.TreatmentTaskStatus;
import com.ai.omed.treatment.model.recurrement.Day;
import com.ai.omed.treatment.model.recurrement.HoursMinutes;
import com.ai.omed.treatment.model.recurrement.Occurrence;
import com.ai.omed.treatment.model.recurrement.Recurrence;
import com.ai.omed.treatment.repository.TreatmentPlanRepository;
import com.ai.omed.treatment.repository.TreatmentTaskRepository;
import com.ai.omed.treatment.util.RecurrenceParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TasksService {

  public static final ZoneId ZONE_ID = ZoneId.systemDefault();
  private final TreatmentTaskRepository treatmentTaskRepository;
  private final TreatmentPlanRepository treatmentPlanRepository;

  @Autowired
  public TasksService(TreatmentTaskRepository treatmentTaskRepository, TreatmentPlanRepository treatmentPlanRepository) {
    this.treatmentTaskRepository = treatmentTaskRepository;
    this.treatmentPlanRepository = treatmentPlanRepository;
  }

  private static LocalDateTime getMax(LocalDateTime dt1, LocalDateTime dt2) {
    return (dt1.isAfter(dt2)) ? dt1 : dt2;
  }

  @Async
  @Scheduled(cron = "0 0/5 * * * ?") // Every 5 minutes
  public int generateTreatmentTasksForOneMonth() {
    log.info("create tasks");
    AtomicInteger totalCount = new AtomicInteger();
    treatmentPlanRepository.findAll()
        .stream()
        .filter(p -> p.getStatus() == TreatmentPlanStatus.ACTIVE)
        .forEach(p -> totalCount.addAndGet(generateTasksForPlan(p)));
    log.info("{} tasks created", totalCount);
    return totalCount.get();
  }

  @Transactional
  public int generateTasksForPlan(TreatmentPlan treatmentPlan) {

    LocalDateTime planProccessingTime = LocalDateTime.now();
    LocalDateTime endTime = treatmentPlan.getEndTime();

    if (endTime != null && endTime.isBefore(planProccessingTime)) {
      log.error("Plan is completed");
      treatmentPlan.setStatus(TreatmentPlanStatus.COMPLETED);
      saveTreatmentPlan(treatmentPlan, planProccessingTime);
      return 0;
    }

    LocalDateTime startTime =
        treatmentPlan.getLastProcessedTime() != null ? getMax(treatmentPlan.getLastProcessedTime(), treatmentPlan.getStartTime()) : treatmentPlan.getStartTime();
    LocalDateTime oneMonthFromNow = planProccessingTime.plus(Duration.ofDays(10));
    // Determine end limit (either plan's endTime or one month from now)
    LocalDateTime limit = (endTime != null && endTime.isBefore(oneMonthFromNow)) ? endTime : oneMonthFromNow;

    Recurrence recurrence = RecurrenceParser.parseRecurrence(treatmentPlan.getRecurrence());
    List<LocalDateTime> nextDateTimes = getNextDateTime(startTime, limit, recurrence);

    List<TreatmentTask> tasks = nextDateTimes.stream().map(i -> {
      TreatmentTask task = new TreatmentTask();
      task.setPatient(treatmentPlan.getPatient());
      task.setStartTime(i);
      task.setAction(treatmentPlan.getAction());
      task.setStatus(TreatmentTaskStatus.ACTIVE);
      return task;
    }).toList();

    // Save to database
    treatmentTaskRepository.saveAll(tasks);

    tasks.stream().max(Comparator.comparing(TreatmentTask::getStartTime))
        .ifPresentOrElse(latestTask -> saveTreatmentPlan(treatmentPlan, latestTask.getStartTime()),
            () -> saveTreatmentPlan(treatmentPlan, planProccessingTime));

    log.info("{} tasks were generated for plan {}", tasks.size(), treatmentPlan.getId());
    return tasks.size();
  }

  private List<LocalDateTime> getNextDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime, Recurrence recurrence) {

    List<LocalDateTime> result = new ArrayList<>();

    int i = 0;
    LocalDateTime currentDateTime = startDateTime;
    while (currentDateTime.isBefore(endDateTime)) {
      currentDateTime = startDateTime.plusDays(i++);
      DayOfWeek currentDay = currentDateTime.getDayOfWeek();

      for (Occurrence occurrence : recurrence.getOccurrences()) {
        if (matchesDay(occurrence.getDay(), currentDay)) {
          for (HoursMinutes time : occurrence.getHoursMinutes()) {
            LocalDateTime candidateDateTime = currentDateTime.withHour(time.getHours()).withMinute(time.getMinutes()).withSecond(0);

            if (!candidateDateTime.isBefore(currentDateTime)) {
              result.add(candidateDateTime);
            }
          }
        }
      }
    }

    return result;

  }

  private boolean matchesDay(Day recurrenceDay, DayOfWeek currentDay) {
    return switch (recurrenceDay) {
      case EVERYDAY -> true;
      case WEEKEND -> currentDay == DayOfWeek.SATURDAY || currentDay == DayOfWeek.SUNDAY;
      case WEEKDAY -> currentDay != DayOfWeek.SATURDAY && currentDay != DayOfWeek.SUNDAY;
      default -> recurrenceDay.name().equalsIgnoreCase(currentDay.name());
    };
  }

  private void saveTreatmentPlan(TreatmentPlan plan, LocalDateTime processTime) {
    if (plan.getLastProcessedTime() == null || processTime.isAfter(plan.getLastProcessedTime())) {
      plan.setLastProcessedTime(processTime.plus(1, ChronoUnit.MINUTES));
    }
    treatmentPlanRepository.save(plan);
  }
}

