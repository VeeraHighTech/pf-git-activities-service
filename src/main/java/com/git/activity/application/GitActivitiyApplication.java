package com.git.activity.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@Configuration
@ComponentScan(value = "com.git.activity.*")
@EnableSwagger2
public class GitActivitiyApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(GitActivitiyApplication.class, args);
		
	}
	
	@Bean
    public Docket saggerapi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.git.activity.controller"))
                .build();
    }
	
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages/git-activities-error-messages");
		return messageSource;
	}
	
	    
}
