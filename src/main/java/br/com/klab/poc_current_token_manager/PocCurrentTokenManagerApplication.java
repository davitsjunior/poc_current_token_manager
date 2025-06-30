package br.com.klab.poc_current_token_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
public class PocCurrentTokenManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocCurrentTokenManagerApplication.class, args);
	}

}
