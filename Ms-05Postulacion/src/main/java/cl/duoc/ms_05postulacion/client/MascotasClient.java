package cl.duoc.ms_05postulacion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.ms_05postulacion.dto.MascotaDto;
@FeignClient(name = "ms-mascotas", url = "http://localhost:8083")
public interface MascotasClient {
@GetMapping("/api/v1/mascotas/{id}")
MascotaDto buscarMascotaPorId(@PathVariable("id") Integer id);
}
