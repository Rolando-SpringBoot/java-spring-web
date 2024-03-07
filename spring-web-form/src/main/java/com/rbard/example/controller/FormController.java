package com.rbard.example.controller;

import com.rbard.example.model.domain.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/*
  @SessionAttributes almacena objetos en la sesión.
 */
@Controller
@SessionAttributes("user")
public class FormController {

  @GetMapping("/form")
  public String form(Model model) {
    User user = new User();
    user.setIdentity("123.456.789-k");
    user.setFirstname("John");
    user.setLastname("Doe");
    model.addAttribute("title", "Formulario usuarios");
    model.addAttribute("user", user);
    return "form";
  }

  /*
    @ModelAttribute me permite cambiar el nombre del (objeto) que por defecto
    se pasa como atributo a la vista.
   */
  @PostMapping("/form")
  public String procesar(Model model,
//      @Valid @ModelAttribute("myuser") User user,
      @Valid User user, BindingResult result, SessionStatus status) {
//      @RequestParam("username") String username,
//      @RequestParam String password,
//      @RequestParam String email) {
//    User user = new User();
//    user.setUsername(username);
//    user.setEmail(email);
//    user.setPassword(password);

    if(result.hasFieldErrors()) {
//      Map<String, String> errors = new HashMap<>();
//      result.getFieldErrors().forEach(fieldError -> {
//        errors.put(fieldError.getField(),
//            STR."El campo \{fieldError.getField()} \{fieldError.getDefaultMessage()}");
//      });
      model.addAttribute("title", "Formulario usuarios");
//      model.addAttribute("error", errors);
      /*
        Completa el proceso y de forma automática se eliminan los objetos que están
        almacenados en la sesión.
       */
      return "form";
    }

    model.addAttribute("title", "Resultado form");

    /*
      Thymeleaf automáticamente pasa el "User user" por attribute, por lo que sería necesario
      esta línea de código.
     */
    model.addAttribute("user", user);
    status.setComplete();
    return "resultado";
  }

}
