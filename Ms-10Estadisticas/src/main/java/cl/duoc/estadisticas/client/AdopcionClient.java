package cl.duoc.estadisticas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.duoc.estadisticas.dto.AdopcionDTO;

@FeignClient(name = "msprocesoadopcion", url = "http://localhost:8086")
public interface AdopcionClient {
    @GetMapping("api/v1/adopciones")
    AdopcionDTO listarAdopciones();

}
