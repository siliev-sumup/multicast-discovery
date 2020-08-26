package com.forthset.search.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers(
        "/actuator/health/**",
          "/v2/api-docs",
          "/swagger-resources/**",
          "/swagger-ui.html",
          "/webjars/**",
          "/search/**"
      ).permitAll()
      .antMatchers("/**").authenticated()
    .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
      .csrf().disable();

  }

}
