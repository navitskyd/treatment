package com.ai.omed.treatment.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class TreatmentTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TreatmentAction action;

  @ManyToOne
  private Patient patient;

  @Column(nullable = false)
  private Instant startTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TreatmentTaskStatus status;

}
