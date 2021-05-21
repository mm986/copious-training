package com.copious.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;


/**
 * @author Mahesh More
 * <p>
 * Configuration class to setup swagger 2
 */
@Configuration
@EnableSwagger2
@Profile("local")
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(
                        newArrayList(new ApiKey("Authorization-Key",
                                "Authorization",
                                "header")))
                .securityContexts(
                        newArrayList(SecurityContext
                                .builder()
                                .securityReferences(
                                        newArrayList(SecurityReference
                                                .builder()
                                                .reference("Authorization-Key")
                                                .scopes(new AuthorizationScope[]{
                                                        new AuthorizationScopeBuilder()
                                                                .scope("global")
                                                                .description("full access")
                                                                .build()
                                                })
                                                .build()))
                                .build()))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Order Processing System")
                .description("Copious training assignment app to demonstrate basics concepts of Java 8.")
                .termsOfServiceUrl("Open")
                .contact(new Contact("Mahesh More",
                        "http://localhost:8080/assignment1",
                        "mahesh@copious.cloud"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/LICENSE")
                .version("2.0")
                .build();
    }

}
