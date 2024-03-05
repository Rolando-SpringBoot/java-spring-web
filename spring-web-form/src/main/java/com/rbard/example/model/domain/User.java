package com.rbard.example.model.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

  @NotBlank
  private String username;
  @NotBlank
  private String password;
  @NotBlank
  private String email;

}
