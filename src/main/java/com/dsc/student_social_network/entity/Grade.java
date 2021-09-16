package com.dsc.student_social_network.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Grade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id")
  private Course course;

  private double grade;

  public Grade() {
    super();
  }

  public Grade(Course course, double grade) {
    this.course = course;
    this.grade = grade;
  }
}
