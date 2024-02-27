package com.rbard.example.fundamental.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
/*
  @PropertySource => Cargar otra clase de configuración
     encoding = UTF-8 -> It allow you to use special characters in yaml file
 */
@PropertySource(value = "classpath:values.yaml", factory = YamlPropertySourceFactory.class, encoding = "UTF-8")
public class ValuesConfig {

}
