package com.ai.omed.treatment.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false)
  private LocalDate birthDate;

}