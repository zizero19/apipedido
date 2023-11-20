package com.br.apipedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ApipedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApipedidoApplication.class, args);
	}

}
