package cl.duoc.registroeventos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.registroeventos.dto.MascotaDTO;

@FeignClient(name = "ms-mascotas", url = "http://localhost:8083")
public interface MascotaClient {

    @GetMapping("api/v1/mascotas/{id}")
    MascotaDTO buscarMascotaPorId(@PathVariable Integer id);

}
