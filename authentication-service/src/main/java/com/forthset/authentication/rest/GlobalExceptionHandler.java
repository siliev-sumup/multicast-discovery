package com.forthset.authentication.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.zalando.problem.Problem;
import org.zalando.problem.spring.common.HttpStatusAdapter;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@ControllerAdvice
public class GlobalExceptionHandler implements ProblemHandling {

  @ExceptionHandler
  public ResponseEntity<Problem> handleClientException(HttpClientErrorException exception) {

    return ResponseEntity
        .status(exception.getStatusCode())
        .body(
          Problem.builder().withStatus(new HttpStatusAdapter(exception.getStatusCode()))
            .withTitle(exception.getLocalizedMessage())
            .build());
  }
}
