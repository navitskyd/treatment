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
@Setter
@Getter
public class TreatmentTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TreatmentAction action;

  @ManyToOne(fetch = FetchType.LAZY)
  private Patient patient;

  @Column(nullable = false)
  private LocalDateTime startTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TreatmentTaskStatus status;

}
