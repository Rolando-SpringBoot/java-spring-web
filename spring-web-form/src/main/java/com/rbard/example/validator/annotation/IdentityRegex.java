package com.rbard.example.validator.annotation;

import com.rbard.example.validator.IdentityRegexValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IdentityRegexValidator.class)
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdentityRegex {

  String message() default "Identity invalid";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
