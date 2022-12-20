package com.example.task.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@Configuration

public class SpringFoxConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.task"))
                .build()
                .apiInfo(createApiInfo());

    }

    private ApiInfo createApiInfo(){
        return new ApiInfo("WeatherApp Rest API",
                "Service integrating with OpenWeatherApi (Atos internship training project)",
                "1.00",
                "sad",
                new Contact("Łukasz", "", "wojcikowski1@gmail.com"),
                "some licence key",
                "ds",
                Collections.emptyList());
    }
}
