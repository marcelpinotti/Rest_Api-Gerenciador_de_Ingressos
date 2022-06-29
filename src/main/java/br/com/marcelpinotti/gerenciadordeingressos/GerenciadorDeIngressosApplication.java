package br.com.marcelpinotti.gerenciadordeingressos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GerenciadorDeIngressosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeIngressosApplication.class, args);
	}

}
