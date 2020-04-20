package com.santosh.rewards;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * To enable Swagger api docs user interface
 * http://localhost:8080/{app-context-root}/api/v2/api-docs for JSON response
 * http://localhost:8080/{app-context-root}/swagger-ui.html for nice UI
 * @author Santosh Singh
 *
 */
@Configuration
@EnableSwagger2
public class SpringFoxSwaggerConfig {                                    
    
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage( "com.santosh.rewards" ))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
}