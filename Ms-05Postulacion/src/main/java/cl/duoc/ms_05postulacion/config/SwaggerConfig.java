package cl.duoc.ms_05postulacion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS-05 Postulación API")
                        .version("1.0.0")
                        .description("API para la gestión de postulaciones en el sistema MS-05.")
                        
                );
    }
}
