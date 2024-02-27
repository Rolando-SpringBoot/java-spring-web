package com.rbard.example.fundamental.controller;

import com.rbard.example.model.User;
import com.rbard.example.model.dto.ParamDto;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

  @Value("${config.code}")
  private Integer code;
  @Value("${config.username}")
  private String username;
  @Value("${config.listOfValues}")
  private List<String> listOfValues;
  @Value("#{'${config.listOfValues}'.toUpperCase().split(',')}")
  private List<String> valueList;
  @Value("#{'${config.listOfValues}'.toUpperCase()}")
  private String valueString;
  @Value("#{${config.valuesMap}}")
  private Map<String, Object> valueMap;
  @Value("#{${config.valuesMap}.product}")
  private String product;
  @Value("#{${config.valuesMap}.price}")
  private Long price;
  @Autowired
  private Environment env;

  @GetMapping("/baz/{message}")
  public ParamDto baz(@PathVariable String message) {
    ParamDto param = new ParamDto();
    param.setMessage(message);
    return param;
  }

  @GetMapping("/mix/{product}/{id}")
  public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {
    Map<String, Object> json = new HashMap<>();
    json.put("product", product);
    json.put("id", id);
    return json;
  }

  @PostMapping("/create")
  public User create(@RequestBody User user) {
    // hacer algo con el usuario save en la bbdd
    user.setName(user.getName().toUpperCase());
    return user;
  }

  @PostMapping(value = "/create-form-data", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public Map<String, Object> createFormData(HttpServletRequest request) {
    Map<String, Object> map = new HashMap<>();
    map.put("name", request.getParameter("name"));
    map.put("lastname", request.getParameter("lastname"));
    map.put("email", request.getParameter("email"));

    return map;
  }

  @GetMapping("/values")
  public Map<String, Object> values(@Value("${config.message}") String message) {
    Map<String, Object> json = new HashMap<>();
    json.put("username", username);
    json.put("code", code);
    json.put("message", message);
    json.put("message2", env.getProperty("config.message"));
    json.put("code2", env.getProperty("config.code", Integer.class));
    json.put("listOfValues", listOfValues);
    json.put("valueString", valueString);
    json.put("valueList", valueList);
    json.put("valueMap", valueMap);
    json.put("product", product);
    json.put("price", price);
    return json;
  }

}
