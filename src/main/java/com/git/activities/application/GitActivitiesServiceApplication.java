package com.git.activities.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@Configuration
@ComponentScan(value="com.git.activities.*")
@EnableSwagger2
public class GitActivitiesServiceApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(GitActivitiesServiceApplication.class, args);
		
	}
	
	@Bean
    public Docket saggerapi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.git.activities.controller"))
                .build();
    }
	
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
}
