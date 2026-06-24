package cl.duoc.estadisticas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EstadisticasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstadisticasApplication.class, args);
	}

}
