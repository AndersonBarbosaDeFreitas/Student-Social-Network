package com.dsc.student_social_network.dto;

import lombok.Data;

@Data
public class GradeDto {
  private double grade;

  public GradeDto() {
    super();
  }

  public GradeDto(double grade) {
    this.grade = grade;
  }
}
