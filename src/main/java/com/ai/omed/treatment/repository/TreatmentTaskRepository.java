package com.ai.omed.treatment.repository;

import java.math.BigInteger;

import com.ai.omed.treatment.model.TreatmentTask;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TreatmentTaskRepository extends JpaRepository<TreatmentTask, Integer> {

}
