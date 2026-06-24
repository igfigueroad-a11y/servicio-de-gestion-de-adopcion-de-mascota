package cl.duoc.mscontrolpostadopcion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class MscontrolpostadopcionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscontrolpostadopcionApplication.class, args);
	}

}
