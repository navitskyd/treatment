package com.ai.omed.treatment.repository;

import com.ai.omed.treatment.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
