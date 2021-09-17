package com.dsc.student_social_network.exception;

import lombok.Getter;

@Getter
public class MainException extends RuntimeException {
  private final int errorStatus;
  private final String errorOrigin;
  private final String message;

  public MainException(String message, int errorStatus, String errorOrigin) {
    super();
    this.errorStatus = errorStatus;
    this.errorOrigin = errorOrigin;
    this.message = message;
  }
}
