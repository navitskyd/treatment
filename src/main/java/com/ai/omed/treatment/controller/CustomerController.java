package com.ai.omed.treatment.controller;

import java.math.BigInteger;
import java.util.Optional;

import com.ai.omed.treatment.model.Patient;
import com.ai.omed.treatment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService service;

  @GetMapping("/{id}")
  public Optional<Patient> getById(@PathVariable Integer id){
    return service.getById(id);
  }
}
