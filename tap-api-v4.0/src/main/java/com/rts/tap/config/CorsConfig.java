package com.rts.tap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

 @Override
 public void addCorsMappings(CorsRegistry registry) {
     // Configure global CORS mapping
     registry.addMapping("/**")  // Allow CORS for all endpoints
             .allowedOrigins("http://localhost:3000")  // Allow React app on localhost:3000
             .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow these methods
             .allowedHeaders("*")  // Allow all headers
             .allowCredentials(true);  // Allow credentials like cookies or authorization headers
 }
}
