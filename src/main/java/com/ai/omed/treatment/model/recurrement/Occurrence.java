package com.ai.omed.treatment.model.recurrement;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Occurrence {

  private Day day;
  private List<HoursMinutes> hoursMinutes;

  @Override
  public String toString() {
    return "Occurrence{" + "day=" + day + ", hoursMinutes=" + hoursMinutes + '}';
  }
}
