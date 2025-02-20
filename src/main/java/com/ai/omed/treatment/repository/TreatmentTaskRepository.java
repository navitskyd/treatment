package com.ai.omed.treatment.repository;

import com.ai.omed.treatment.model.TreatmentTask;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TreatmentTaskRepository extends JpaRepository<TreatmentTask, Integer> {

}
