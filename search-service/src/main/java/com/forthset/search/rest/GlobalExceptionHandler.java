package com.forthset.search.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.common.HttpStatusAdapter;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class GlobalExceptionHandler implements ProblemHandling {

  @ExceptionHandler
  public ResponseEntity<Problem> handleArgumentException(IllegalArgumentException exception) {

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
            Problem.builder().withStatus(new HttpStatusAdapter(HttpStatus.INTERNAL_SERVER_ERROR))
                .withTitle(exception.getLocalizedMessage())
                .build());
  }
}
