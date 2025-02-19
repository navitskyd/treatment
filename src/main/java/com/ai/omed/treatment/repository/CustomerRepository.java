package com.ai.omed.treatment.repository;

import java.math.BigInteger;

import com.ai.omed.treatment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Patient, Integer> {
}
