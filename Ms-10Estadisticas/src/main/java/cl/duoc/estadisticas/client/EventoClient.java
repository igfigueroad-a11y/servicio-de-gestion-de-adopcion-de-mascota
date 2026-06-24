package cl.duoc.estadisticas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.duoc.estadisticas.dto.EventoDTO;

@FeignClient(name = "registroeventos", url = "http://localhost:8089")
public interface EventoClient {
    @GetMapping("api/v1/eventos")
    EventoDTO listarEventos();

}
