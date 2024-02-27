package com.rbard.example.fundamental.controller;

import com.rbard.example.model.dto.ParamDto;
import com.rbard.example.model.dto.ParamMixDto;
import com.rbard.example.util.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/params")
public class RequestParamController {

  @GetMapping("/foo")
  public ParamDto foo(@RequestParam(required = false, defaultValue = "Hola que tal!") String message) {
    ParamDto param = new ParamDto();
    param.setMessage(message);
    return param;
  }

  @GetMapping("/bar")
  public ParamMixDto bar(@RequestParam String text, @RequestParam Integer code) {
    ParamMixDto params = new ParamMixDto();
    params.setMessage(text);
    params.setCode(code);
    return params;
  }

  @GetMapping("/request")
  public ParamMixDto request(HttpServletRequest request) {
    String message = request.getParameter("message");
    int code = Integer.parseInt(Optional.ofNullable(request.getParameter("code"))
              .filter(StringUtil::isDigitOnly)
              .orElse("0"));

    ParamMixDto params = new ParamMixDto();
    params.setCode(code);
    params.setMessage(message);
    return params;
  }

}
