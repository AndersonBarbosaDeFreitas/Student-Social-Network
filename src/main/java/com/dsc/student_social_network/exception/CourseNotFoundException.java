package com.dsc.student_social_network.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CourseNotFoundException extends MainException {

  public CourseNotFoundException(String message, String errorOrigin) {
    super(message, HttpStatus.NOT_FOUND.value(), errorOrigin);
  }

}
