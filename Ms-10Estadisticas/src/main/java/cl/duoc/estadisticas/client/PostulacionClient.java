package cl.duoc.estadisticas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.duoc.estadisticas.dto.PostulacionDTO;

@FeignClient(name = "ms-05postulacion", url = "http://localhost:8085")
public interface PostulacionClient {
    @GetMapping("api/v1/postulaciones")
    PostulacionDTO listarPostulaciones();

}
