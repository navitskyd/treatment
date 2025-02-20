package com.ai.omed.treatment.service;

import java.util.Optional;

import com.ai.omed.treatment.model.Patient;
import com.ai.omed.treatment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

  @Autowired
  private PatientRepository repository;


  public Optional<Patient> getById(Integer id) {
    return repository.findById(id);
  }

}
