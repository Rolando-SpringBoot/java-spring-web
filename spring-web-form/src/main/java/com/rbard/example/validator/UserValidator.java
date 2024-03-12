package com.rbard.example.validator;

import com.rbard.example.model.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return User.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    User user = (User) target;
    // validating firstname value
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotBlank.user.firstname");

    // validating identity value
//    if(!user.getIdentity().matches("\\d{2}[.,]\\d{3}[.,]\\d{3}-[A-Z]")) {
//      errors.rejectValue("identity", "pattern.user.identity");
//    }
  }

}
