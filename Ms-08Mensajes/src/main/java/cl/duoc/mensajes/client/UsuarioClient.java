package cl.duoc.mensajes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.mensajes.dto.UsuarioDTO;

@FeignClient(name = "msusarios", url = "http://localhost:8081")
public interface UsuarioClient {
    @GetMapping("api/v1/usuarios/{id}")
    UsuarioDTO buscarUsuarioPorId(@PathVariable Integer id);

}
