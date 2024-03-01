package com.rbard.example.fundamental.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component("timeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

  /*
    Si preHandle devuelve un false, ya no continua con la ejecución del método controlador
    ni de los demás interceptores.
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HandlerMethod controller = (HandlerMethod) handler;
    log.atInfo().setMessage("LoadingTimeInterceptor: preHandle() entrando ... {}")
            .addArgument(controller.getMethod().getName()).log();
    long start = System.currentTimeMillis();
    request.setAttribute("start", start);

    Random random = new Random();
    int delay = random.nextInt(500);
    TimeUnit.MILLISECONDS.sleep(delay);

    /*
      leyendo payload que viene del request
     */
    Map<String, Object> requestBody = new ObjectMapper().readValue(request.getInputStream(),
        new TypeReference<HashMap<String, Object>>() {});
    log.atInfo().setMessage("requestBody={}").addArgument(requestBody).log();

    /*
      leyendo headers
     */
    String headerContentType = request.getHeader("content-type");
    log.atInfo().setMessage("contentType={}").addArgument(headerContentType).log();

    /*
      leyendo request params
     */
    String parameter = request.getParameter("id");
    log.atInfo().setMessage("parameter={}").addArgument(parameter).log();

    /*
      personalizando respuesta en caso de devolver un false
     */
//    Map<String, String> json = new HashMap<>();
//    json.put("error", "no tiene acceso a esta pagina!");
//    json.put("date", new Date().toString());
//
//    ObjectMapper mapper = new ObjectMapper();
//    String jsonString = mapper.writeValueAsString(json);
//    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//    response.getWriter().write(jsonString);
//    return false;
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

    long start = (long) request.getAttribute("start");
    long end = System.currentTimeMillis();
    long result = end - start;
    log.info("Tiempo transcurrido: " + result + " milisegundos!");
    log.atInfo().setMessage("Tiempo transcurrido: {} milisegundos!")
        .addArgument(result).log();

    HandlerMethod controller = (HandlerMethod)  handler;
    log.atInfo().setMessage("LoadingTimeInterceptor: postHandle() saliendo ... {}")
        .addArgument(controller.getMethod().getName()).log();
  }

}
