package cl.duoc.Ms_02Acceso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class Ms02AccesoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ms02AccesoApplication.class, args);
	}

}
