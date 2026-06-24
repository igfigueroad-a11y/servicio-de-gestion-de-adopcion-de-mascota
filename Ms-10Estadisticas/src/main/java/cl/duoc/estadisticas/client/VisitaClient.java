package cl.duoc.estadisticas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.duoc.estadisticas.dto.VisitaDTO;

@FeignClient(name = "mscontrolpostadopcion", url = "http://8087")
public interface VisitaClient {

    @GetMapping("api/v1/visitas")
    VisitaDTO listarVisitas();
}
