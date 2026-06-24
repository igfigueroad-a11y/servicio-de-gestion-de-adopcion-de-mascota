package cl.duoc.registroeventos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.registroeventos.dto.AdopcionDTO;

@FeignClient(name = "msprocesoadopcion", url = "http://localhost:8086")
public interface AdopciocClient {
    @GetMapping("api/v1/adopciones/{id}")
    AdopcionDTO buscarAdopcionPorId(@PathVariable Integer id);
    

}
