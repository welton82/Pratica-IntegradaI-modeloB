package com.eug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
					//remover parte entre parenteses)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TrabalhoPraticaIntegradaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoPraticaIntegradaApplication.class, args);
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder;
	}

}
/*NESSE PROJETO FAZEMOS A CRIPTOGRAFIA DA SENHA E AUTENTICAÇÃO DO LOGIN e 
 * TOKEN JWT 
 *
 *  * */
 