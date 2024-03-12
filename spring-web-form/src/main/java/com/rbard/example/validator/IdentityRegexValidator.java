package com.rbard.example.validator;

import com.rbard.example.validator.annotation.IdentityRegex;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IdentityRegexValidator implements ConstraintValidator<IdentityRegex, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return value.matches("\\d{2}[.,]\\d{3}[.,]\\d{3}-[A-Z]");
  }

}
