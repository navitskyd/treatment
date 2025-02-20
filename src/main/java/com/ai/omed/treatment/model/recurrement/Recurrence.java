package com.ai.omed.treatment.model.recurrement;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Recurrence {

  private List<Occurrence> occurrences;

  @Override
  public String toString() {
    return "Recurrence{" + "occurrences=" + occurrences + '}';
  }
}
