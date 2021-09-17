package com.dsc.student_social_network.exception.advice;

import com.dsc.student_social_network.exception.CourseNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

  private static String LOG_IN = "https://localhost:8080/v1/api/login";

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UsernameNotFoundException.class)
  public ErrorResponseBody handleAccessDeniedException(UsernameNotFoundException e) {
    ErrorResponseBody error = new ErrorResponseBody();
    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setOrigin(LOG_IN);
    error.setDetail(e.getMessage());
    return error;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(CourseNotFoundException.class)
  public ErrorResponseBody handleCourseNotFoundException(CourseNotFoundException e) {
    ErrorResponseBody error = new ErrorResponseBody();
    error.setStatus(e.getErrorStatus());
    error.setOrigin(e.getErrorOrigin());
    error.setDetail(e.getMessage());
    return error;
  }

}
