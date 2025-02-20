package com.ai.omed.treatment.controller;

import java.util.Optional;

import com.ai.omed.treatment.model.Patient;
import com.ai.omed.treatment.service.PatientService;
import com.ai.omed.treatment.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

  private final PatientService service;


  @Autowired
  public PatientController(PatientService service) {
    this.service = service;
  }


  @GetMapping("/{id}")
  public Optional<Patient> getById(@PathVariable Integer id) {
    return service.getById(id);
  }
}
