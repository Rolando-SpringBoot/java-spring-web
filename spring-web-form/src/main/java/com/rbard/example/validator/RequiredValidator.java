package com.rbard.example.validator;

import com.rbard.example.validator.annotation.Required;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class RequiredValidator implements ConstraintValidator<Required, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return StringUtils.hasText(value);
  }

}
