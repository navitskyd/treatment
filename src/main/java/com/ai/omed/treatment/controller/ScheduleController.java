package com.ai.omed.treatment.controller;

import com.ai.omed.treatment.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

  private final TasksService tasksService;

  @Autowired
  public ScheduleController(TasksService tasksService) {
    this.tasksService = tasksService;
  }

  @GetMapping("/tasks/plan")
  public int planTasks() {
    return tasksService.generateTreatmentTasksForOneMonth();
  }
}
