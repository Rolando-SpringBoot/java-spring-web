package com.rbard.example.controller;

import com.rbard.example.editor.NameUpperCaseEditor;
import com.rbard.example.model.domain.User;
import com.rbard.example.validator.UserValidator;
import jakarta.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/*
  @SessionAttributes almacena objetos en la sesión.
 */
@Controller
@SessionAttributes("user")
public class FormController {

  @Autowired
  private UserValidator userValidator;

  /*
    @InitBinder -> This annotation allows you to customize the databinding process.
                   This process start when you use @Valid to some class.
                   So We are customizing this process to add another validator.
   */
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(this.userValidator);
    binder.registerCustomEditor(String.class, "firstname", new NameUpperCaseEditor());
    binder.registerCustomEditor(String.class, "lastname", new NameUpperCaseEditor());
  }

  @ModelAttribute("countries")
  public List<String> countries() {
    return List.of("España", "Mexico", "Chile", "Argentina", "Perú", "Colombia", "Venezuela");
  }

  @GetMapping("/form")
  public String form(Model model) {
    User user = new User();
    user.setIdentity("12.456.789-K");
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
//    this.userValidator.validate(user, result);
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
