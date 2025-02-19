package com.ai.omed.treatment.repository;

import java.math.BigInteger;

import com.ai.omed.treatment.model.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Integer> {
}
