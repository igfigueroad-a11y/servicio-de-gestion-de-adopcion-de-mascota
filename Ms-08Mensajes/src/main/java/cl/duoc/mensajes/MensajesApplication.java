package cl.duoc.mensajes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MensajesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MensajesApplication.class, args);
	}

}
