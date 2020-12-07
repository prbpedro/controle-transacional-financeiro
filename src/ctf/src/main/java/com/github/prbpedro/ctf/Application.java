package com.github.prbpedro.ctf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.github.prbpedro.ctf.services.IOperationTypeService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		context.getBean(IOperationTypeService.class).persistDefaultValues();
	}
}
