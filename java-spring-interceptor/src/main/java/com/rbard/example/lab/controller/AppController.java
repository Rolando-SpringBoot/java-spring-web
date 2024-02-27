package com.rbard.example.lab.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

  @GetMapping("/foo")
  public ResponseEntity<?> foo(HttpServletRequest request) {
    Map<String, Object> data = new HashMap<>();
    data.put("title", "Bienvenido al sistema de atención");
    data.put("time", ZonedDateTime.now(ZoneId.systemDefault()));
    data.put("message", request.getAttribute("message"));
    return new ResponseEntity<>(data, HttpStatus.OK);
  }

}
