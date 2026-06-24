package cl.duoc.estadisticas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.duoc.estadisticas.dto.UsuarioDTO;

@FeignClient(name = "msusarios", url = "http://localhost:8081")
public interface UsuarioClient {
    @GetMapping("api/v1/usuarios")
    UsuarioDTO listarUsuarios();

}
