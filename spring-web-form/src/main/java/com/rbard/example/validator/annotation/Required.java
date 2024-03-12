package com.rbard.example.validator.annotation;

import com.rbard.example.validator.RequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RequiredValidator.class)
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {

  String message() default "the field is required - custom annotation";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
