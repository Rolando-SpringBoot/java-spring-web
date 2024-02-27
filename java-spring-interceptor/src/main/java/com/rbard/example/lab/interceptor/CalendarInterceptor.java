package com.rbard.example.lab.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.rbard.example.lab.configuration.JsonZonedDateTimeSerialize;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class CalendarInterceptor implements HandlerInterceptor {

  @Value("${config.calendar.open}")
  private Integer open;
  @Value("${config.calendar.close}")
  private Integer close;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
    int hour = now.getHour();
    log.atInfo().setMessage("hour={}").addArgument(hour).log();

    if (hour >= open && hour < close) {
      StringBuilder message = new StringBuilder("Bienvenidos al horario de atención al cliente");
      message.append(", atendemos desde las ");
      message.append(open);
      message.append("hrs. ");
      message.append("hasta las ");
      message.append(close);
      message.append("hrs. ");
      message.append("Gracias por su visita! ");
      request.setAttribute("message", message.toString());
      return true;
    }

    StringBuilder message = new StringBuilder("Cerrado, fuera del horario de atención ");
    message.append("por favor visítenos desde las ");
    message.append(open);
    message.append("hrs. ");
    message.append("hasta las");
    message.append(close);
    message.append("hrs. ");
    message.append("Gracias!");

    ObjectMapper mapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(ZonedDateTime.class, new JsonZonedDateTimeSerialize());
    mapper.registerModule(simpleModule);
    Map<String, Object> data = new HashMap<>();
    data.put("time", ZonedDateTime.now(ZoneId.systemDefault()));
    data.put("message", message.toString());
    response.setContentType("application/json");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.getWriter().write(mapper.writeValueAsString(data));
    return false;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
  }

}
