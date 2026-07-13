package cl.duoc.ms_05postulacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class Ms05postulacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ms05postulacionApplication.class, args);
	}

}
