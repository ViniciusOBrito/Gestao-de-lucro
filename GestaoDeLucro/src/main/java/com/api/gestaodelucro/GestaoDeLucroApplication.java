package com.api.gestaodelucro;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Gestão de Lucro",
        description = "API para gestão de lucro de peças de carne de um açougue"))
public class GestaoDeLucroApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoDeLucroApplication.class, args);
    }

}
