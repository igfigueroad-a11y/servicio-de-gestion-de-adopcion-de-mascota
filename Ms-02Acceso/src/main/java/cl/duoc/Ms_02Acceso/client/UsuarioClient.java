package cl.duoc.Ms_02Acceso.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.Ms_02Acceso.dto.UsuarioDto;
@FeignClient(name = "msusarios", url = "http://localhost:8081")
public interface UsuarioClient {
@GetMapping("/api/v1/usuarios/{id}")
UsuarioDto buscarUsuarioPorId(@PathVariable("id") Integer id);
}
