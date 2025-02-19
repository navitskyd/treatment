package com.ai.omed.treatment.service;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import com.ai.omed.treatment.model.Patient;
import com.ai.omed.treatment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository repository;

  public Optional<Patient> getById(Integer id){
    return repository.findById(id);
  }

}
