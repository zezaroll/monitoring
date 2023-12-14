package com.company.monitoring.configuration;

import com.company.monitoring.repository.MeasurementsRepository;
import com.company.monitoring.repository.UserRepository;
import com.company.monitoring.service.MeasurementsService;
import com.company.monitoring.service.MeasurementsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class AppConfiguration {

    @Bean
    public MeasurementsService getMeasurementsService(MeasurementsRepository measurementsRepository,
                                                      UserRepository userRepository) {
        return new MeasurementsServiceImpl(measurementsRepository, userRepository);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.company.monitoring.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .version("1.0")
                        .title("Simple Gas and Water Monitoring API")
                        .build());
    }
}
