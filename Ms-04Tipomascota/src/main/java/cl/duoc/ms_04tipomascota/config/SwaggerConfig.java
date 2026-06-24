package cl.duoc.ms_04tipomascota.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS-04 Tipo Mascota API")
                        .version("1.0")
                        .description("Documentación de la API del microservicio MS-04 Tipo Mascota"));
    }
}
