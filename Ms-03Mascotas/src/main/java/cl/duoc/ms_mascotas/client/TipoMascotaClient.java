package cl.duoc.ms_mascotas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import cl.duoc.ms_mascotas.dto.TipoMascotaDto;
@FeignClient(name = "ms-04tipomascota", url = "http://localhost:8084")
public interface TipoMascotaClient {
@GetMapping("/api/v1/tipos/{id}")
TipoMascotaDto buscarTipoPorId(@PathVariable("id") Integer id);
}
