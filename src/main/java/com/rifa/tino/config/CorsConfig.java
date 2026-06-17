package com.rifa.tino.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // Aplica a todos los endpoints
        .allowedOrigins("*") // Todos los orígenes
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //Métodos específicos
        .allowedHeaders("*"); //Todos los headers
  }
}