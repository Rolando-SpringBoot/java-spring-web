package com.rbard.example.model.domain;

import com.rbard.example.validator.annotation.IdentityRegex;
import com.rbard.example.validator.annotation.Required;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
public class User {

//  @Pattern(regexp = "\\d{2}[.,]\\d{3}[.,]\\d{3}-[A-Z]", message = "identity format is incorrect")
  @IdentityRegex
  private String identity;
  @NotBlank
  @Size(min = 3, max = 8)
  private String username;
  @NotBlank
  private String password;
  @Email(message = "email with incorrect format")
  @Required
  private String email;

//  @NotBlank(message = "the name can't be empty")
  private String firstname;
//  @NotBlank
  @Required
  private String lastname;

  @NotNull
  @Min(5)
  @Max(5000)
  private Integer account;

  @NotNull
  @Past
//  @Future
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  @NotBlank
  private String country;

}
