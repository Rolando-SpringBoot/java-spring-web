package com.rbard.example.model.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

  private String identity;
  @NotBlank
  @Size(min = 3, max = 8)
  private String username;
  @NotBlank
  private String password;
  @Email(message = "email with incorrect format")
  @NotBlank
  private String email;
  @NotBlank(message = "the name can't be empty")
  private String firstname;
  @NotBlank
  private String lastname;

}
