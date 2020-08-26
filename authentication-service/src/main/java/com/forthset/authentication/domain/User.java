package com.forthset.authentication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Entity for the DB
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

  @Id
  @Email
  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "firstName")
  @NotNull
  private String firstName;

  @Column(name = "lastName")
  @NotNull
  private String lastName;

  @Column(name = "phone")
  private String phone;

  @Column(name = "password")
  @NotNull
  private String password;

}
