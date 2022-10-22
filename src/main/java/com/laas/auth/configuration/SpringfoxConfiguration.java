package com.laas.auth.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringfoxConfiguration {
    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
          .apiInfo(getApiInfo())
          .securityContexts(Arrays.asList(securityContext()))
          .securitySchemes(Arrays.asList(apiKey()))
          .select()
          .apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())
          .build();
    }
    
    private ApiInfo getApiInfo() {
        var builder = new ApiInfoBuilder();
        builder.title("laas-auth");
        builder.description("Auth");
        builder.version("1.0");
        return builder.build();
    }
    
    private SecurityContext securityContext() { 
        return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
    } 
    
    private List<SecurityReference> defaultAuth() { 
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
        authorizationScopes[0] = authorizationScope; 
        return Arrays.asList(new SecurityReference("Bearer Authorization", authorizationScopes)); 
    }
    private ApiKey apiKey() { 
        return new ApiKey("Bearer Authorization", "Authorization", "header"); 
    }
}
