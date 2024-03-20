package com.rbard.example.controller;

import com.rbard.example.editor.CountryPropertyEditor;
import com.rbard.example.editor.NameUpperCaseEditor;
import com.rbard.example.editor.RolePropertyEditor;
import com.rbard.example.model.domain.Country;
import com.rbard.example.model.domain.Role;
import com.rbard.example.model.domain.User;
import com.rbard.example.service.CountryService;
import com.rbard.example.service.RoleService;
import com.rbard.example.validator.UserValidator;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
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
  @Autowired
  private CountryService countryService;
  @Autowired
  private RoleService roleService;
  @Autowired
  private CountryPropertyEditor countryPropertyEditor;
  @Autowired
  private RolePropertyEditor rolePropertyEditor;

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
    binder.registerCustomEditor(Country.class, "country", this.countryPropertyEditor);
    binder.registerCustomEditor(Role.class, "roles", this.rolePropertyEditor);
  }

  @ModelAttribute("countryList")
  public List<Country> countryList() {
    return this.countryService.list();
  }

  @ModelAttribute("countries")
  public List<String> countries() {
    return List.of("España", "Mexico", "Chile", "Argentina", "Perú", "Colombia", "Venezuela");
  }

  @ModelAttribute("countriesMap")
  public Map<String, String> countriesMap() {
    Map<String, String> countries = new HashMap<>();
    countries.put("ES", "España");
    countries.put("MX", "México");
    countries.put("CL", "Chile");
    countries.put("AR", "Argentina");
    countries.put("PE", "Perú");
    countries.put("CO", "Colombia");
    countries.put("VE", "Venezuela");

    return countries;
  }

  @ModelAttribute("listaRolesString")
  public List<String> listaRolesString() {
    return List.of("ROLE_ADMIN", "ROLES_USER", "ROLE_MODERATOR");
  }

  @ModelAttribute("listaRolesMap")
  public Map<String, String> listaRolesMap() {
    Map<String, String> rolesMap = new HashMap<>();
    rolesMap.put("ROLE_ADMIN", "Administrator");
    rolesMap.put("ROLE_USER", "User");
    rolesMap.put("ROLE_MODERATOR", "Moderator");
    return rolesMap;
  }

  @ModelAttribute("roleList")
  public List<Role> roleList() {
    return this.roleService.list();
  }

  @ModelAttribute("genderList")
  public List<String> genderList() {
    return List.of("male", "female");
  }

  @GetMapping("/form")
  public String form(Model model) {
    User user = new User();
    user.setIdentity("12.456.789-K");
    user.setFirstname("John");
    user.setLastname("Doe");
    user.setEnabled(true);
    user.setSecretValue("A secret value ****");
    user.setCountry(Country.of(3, "CL", "Chile"));
    user.setRoles(List.of(
        Role.of(2, "User", "ROLE_USER"),
        Role.of(3, "Moderator", "ROLE_MODERATOR")
    ));

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
      @Valid User user, BindingResult result) {
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
    return "redirect:/ver";
  }

  @GetMapping("/ver")
  public String ver(@SessionAttribute(value = "user", required = false) User user, Model model, SessionStatus status) {

    if(Objects.isNull(user)) {
      return "redirect:/form";
    }

    model.addAttribute("title", "Resultado form");
    status.setComplete();

    /*
      Thymeleaf automáticamente pasa el "User user" por attribute, por lo que sería necesario
      esta línea de código.
     */
    // model.addAttribute("user", user);
    return "resultado";
  }

}
