package com.forthset.authentication.rest.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

  @Pattern(regexp = ".+@.+\\..{2,64}")
  private String email;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  private String phone;

  private String password;
}
