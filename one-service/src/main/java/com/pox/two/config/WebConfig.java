package com.pox.two.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@Configuration
public class WebConfig {

  @Bean
  public ObjectMapper objectMapper() {

    return new ObjectMapper().registerModules(
        new ProblemModule(),
        new ConstraintViolationProblemModule());
  }
}
