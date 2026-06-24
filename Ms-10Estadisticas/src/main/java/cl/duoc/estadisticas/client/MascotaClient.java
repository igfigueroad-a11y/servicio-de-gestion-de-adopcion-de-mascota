package cl.duoc.estadisticas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.duoc.estadisticas.dto.MascotaDTO;

@FeignClient(name = "ms-mascotas", url = "http://localhost:8083")
public interface MascotaClient {

    @GetMapping("api/v1/mascotas")
    MascotaDTO listarMascotas();
}
