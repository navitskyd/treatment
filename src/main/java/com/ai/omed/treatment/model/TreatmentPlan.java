package com.ai.omed.treatment.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TreatmentPlan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  private Patient patient;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TreatmentAction action;

  @Column
  private String recurrence;
  @Column(nullable = false) //timestamp of earliest possible treatment task
  private Instant startTime;

  @Column(nullable = true) //timestamp of the latest possible treatment task or empty if the plan is endless
  private Instant endTime;
}
