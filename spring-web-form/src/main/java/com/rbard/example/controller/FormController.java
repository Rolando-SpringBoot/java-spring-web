package com.rbard.example.controller;

import static java.lang.StringTemplate.STR;

import com.rbard.example.model.domain.User;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {

  @GetMapping("/form")
  public String form(Model model) {
    model.addAttribute("title", "Formulario usuarios");
    return "form";
  }

  /*
    @ModelAttribute me permite cambiar el nombre del (objeto) que por defecto
    se pasa como atributo a la vista.
   */
  @PostMapping("/form")
  public String procesar(Model model, @Valid @ModelAttribute("myuser") User user, BindingResult result) {
//      @RequestParam("username") String username,
//      @RequestParam String password,
//      @RequestParam String email) {
//    User user = new User();
//    user.setUsername(username);
//    user.setEmail(email);
//    user.setPassword(password);

    if(result.hasFieldErrors()) {
      Map<String, String> errors = new HashMap<>();
      result.getFieldErrors().forEach(fieldError -> {
        errors.put(fieldError.getField(),
            STR."El campo \{fieldError.getField()} \{fieldError.getDefaultMessage()}");
      });
      model.addAttribute("title", "Formulario usuarios");
      model.addAttribute("error", errors);
      return "form";
    }

    model.addAttribute("title", "Resultado form");

    /*
      Thymeleaf automáticamente pasa el user por attribute, por lo que podrías no declarar
      esta línea de código.
     */
    model.addAttribute("user", user);
    return "resultado";
  }

}
