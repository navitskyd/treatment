package com.ai.omed.treatment.model.recurrement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HoursMinutes {

  private final Integer hours;
  private final Integer minutes;

  public HoursMinutes(Integer hours, Integer minutes) {
    if (hours == null || hours < 0 || hours > 23) {
      throw new IllegalArgumentException("Hours must be between 0 and 23. Given: " + hours);
    }
    if (minutes == null || minutes < 0 || minutes > 59) {
      throw new IllegalArgumentException("Minutes must be between 0 and 59. Given: " + minutes);
    }
    this.hours = hours;
    this.minutes = minutes;
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d", hours, minutes);
  }
}

