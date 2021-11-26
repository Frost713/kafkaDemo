package com.example.kafkaDemo.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

/**
 * @author Frost
 * @date 2021/11/15 11:02
 */
@Configuration
@EnableSwagger2
@Profile(value = {"staging", "development"})
public class SwaggerConfiguration {
    //swagger local url :http://localhost:8901/swagger-ui/index.html

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("com.xxxxx.controller"))
                .paths(any())
                .build();
    }
}
