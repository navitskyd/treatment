package com.ai.omed.treatment.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TreatmentPlan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Patient patient;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TreatmentAction action;

  @Column(nullable = false)
  private String recurrence;
  @Column(nullable = false) //timestamp of earliest possible treatment task
  private LocalDateTime startTime;

  @Column(nullable = true) //timestamp of the latest possible treatment task or empty if the plan is endless
  private LocalDateTime endTime;

  private LocalDateTime lastProcessedTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TreatmentPlanStatus status;
}
