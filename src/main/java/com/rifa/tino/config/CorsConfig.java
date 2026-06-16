package com.rifa.tino.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")               // Aplica a todos los endpoints
        .allowedOrigins("http://localhost:4200", "https://codesbygabby.com/rifatino/", "https://codesbygabby.com/")  // Origen de tu frontend (ej: Angular)
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")
        .allowCredentials(true);          // Si usas cookies o autenticación
  }
}