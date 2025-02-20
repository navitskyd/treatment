package com.ai.omed.treatment.repository;

import com.ai.omed.treatment.model.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Integer> {

}
