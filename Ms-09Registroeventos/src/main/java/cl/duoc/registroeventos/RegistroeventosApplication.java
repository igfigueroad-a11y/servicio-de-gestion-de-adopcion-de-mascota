package cl.duoc.registroeventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class RegistroeventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistroeventosApplication.class, args);
	}

}
