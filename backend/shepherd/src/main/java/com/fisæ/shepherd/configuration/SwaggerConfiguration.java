package com.fisæ.shepherd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Swagger configuration class
 */
@Configuration
@Import(SpringDataRestConfiguration.class)
public class SwaggerConfiguration {

    /**
     * Package in which the controllers are defined
     */
    private final static String controllersPackage = "com.fisæ.shepherd.api.controllers";

    /**
     * Expose controllers and endpoints to be displayed by Swagger
     *
     * @return The associated Java Bean
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(controllersPackage))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    /**
     * Specify the Swagger UI information do be displayed
     *
     * @return The associated Java Bean
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Shepherd REST API",
                "Shepherd public API to handle the application's resources",
                "v1.0",
                "about:blank",
                new Contact(
                        "Team FISÆ",
                        "https://github.com/pbouillon/shepherd",
                        "shepherd@fisæ.eu"),
                "API License",
                "about:blank",
                Collections.emptyList());
    }

}