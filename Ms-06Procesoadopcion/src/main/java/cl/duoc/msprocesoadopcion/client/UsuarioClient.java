package cl.duoc.msprocesoadopcion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.msprocesoadopcion.dto.UsuarioDTO;

@FeignClient(name = "msusarios", url = "http://localhost:8081")
public interface UsuarioClient {
    @GetMapping("/api/v1/usuarios{id}")
    UsuarioDTO buscaUsuarioPorId(@PathVariable Integer id);

}
