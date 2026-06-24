package cl.duoc.ms_05postulacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class Ms05postulacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ms05postulacionApplication.class, args);
	}

}
