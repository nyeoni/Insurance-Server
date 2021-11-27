package com.hm.insuranceservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(title = "Insurance API", description = "Documentation Insurance-Service")
)
public class InsuranceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceServiceApplication.class, args);
	}

}
