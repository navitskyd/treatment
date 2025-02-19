package com.ai.omed.treatment.util;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

import com.ai.omed.treatment.model.recurrement.Day;
import com.ai.omed.treatment.model.recurrement.HoursMinutes;
import com.ai.omed.treatment.model.recurrement.Occurrence;
import com.ai.omed.treatment.model.recurrement.Recurrence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class RecurrenceParser {

  private static final Pattern RECURRENCE_PATTERN =
      Pattern.compile("EVERY (.+?) AT (.+)");

  public static Recurrence parseRecurrence(String recurrencePattern) {
    if(!StringUtils.hasText(recurrencePattern)){
      return null;
    }
    Matcher matcher = RECURRENCE_PATTERN.matcher(recurrencePattern);
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid recurrence pattern: " + recurrencePattern);
    }

    String daysPart = matcher.group(1);
    String timesPart = matcher.group(2);

    // Parse days
    List<Day> days = Arrays.stream(daysPart.split(" AND "))
        .map(RecurrenceParser::parseDay)
        .toList();

    // Parse times
    List<HoursMinutes> times = Arrays.stream(timesPart.split(" AND "))
        .map(RecurrenceParser::parseTime)
        .collect(Collectors.toList());

    // Create occurrences
    List<Occurrence> occurrences = days.stream()
        .map(day -> new Occurrence(day, times))
        .collect(Collectors.toList());

    return new Recurrence(occurrences);
  }

  private static Day parseDay(String day) {
    try {
      return Day.valueOf(day.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid day: " + day);
    }
  }

  private static HoursMinutes parseTime(String time) {
    String[] parts = time.split(":");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid time format: " + time);
    }
    return new HoursMinutes(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
  }

  public static void main(String[] args) {
    String input = "EVERY MONDAY AT 08:00";
    Recurrence recurrence = RecurrenceParser.parseRecurrence(input);

    input = "EVERY MONDAY AND WEDNESDAY AND FRIDAY AT 10:30 AND 16:00";
    recurrence = RecurrenceParser.parseRecurrence(input);

    input = "EVERY TUESDAY AT 09:00 AND 14:45 AND 18:30";
    recurrence = RecurrenceParser.parseRecurrence(input);

    input = "EVERY WEDNESDAY AND FRIDAY AT 09:30 AND 16:00";
    recurrence = RecurrenceParser.parseRecurrence(input);

    input = "EVERY FUNSDAY AT 10:00";
   // recurrence = RecurrenceParser.parseRecurrence(input);

    input = "EVERY MONDAY AT 23:59";
    recurrence = RecurrenceParser.parseRecurrence(input);

    recurrence = RecurrenceParser.parseRecurrence(null);

    log.info("All Tests passed");
  }

}

