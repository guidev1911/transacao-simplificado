package com.guidev.transacao_simplificada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransacaoSimplificadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransacaoSimplificadaApplication.class, args);
	}

}
