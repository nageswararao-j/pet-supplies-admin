package com.pet.supplies.admin.springboot;

import com.pet.supplies.admin.springboot.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @version
 * @author njanjyal //I removed copyrights
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("com.pet.supplies.admin.repository")
@ComponentScan("com.pet.supplies.admin")
@EntityScan("com.pet.supplies.common.domain")
public class Application extends SpringBootServletInitializer
{
   public static void main(String[] args)
   {
      SpringApplication.run(Application.class, args);
   }

   @Override
   protected final SpringApplicationBuilder configure(SpringApplicationBuilder application)
   {
      return application.sources(Application.class);
   }
   
   @Bean
   public WebMvcConfigurer corsConfigurer()
   {
      return new WebMvcConfigurerAdapter()
         {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
               registry.addMapping("/*").allowedOrigins("${app.allow.origin}");
            }
         };
   }
}