package com.dsc.student_social_network.exception.advice;

import lombok.Data;

@Data
public class ErrorResponseBody {

  private int status;
  private String origin;
  private String detail;

  public ErrorResponseBody() {
    super();
  }
}
