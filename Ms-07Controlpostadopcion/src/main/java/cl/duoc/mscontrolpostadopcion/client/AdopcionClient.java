package cl.duoc.mscontrolpostadopcion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.mscontrolpostadopcion.dto.AdopcionDTO;
import cl.duoc.mscontrolpostadopcion.dto.UsuarioDTO;

@FeignClient(name = "msprocesoadopcion", url = "http://localhost:8086")
public interface AdopcionClient {
    @GetMapping("api/v1/adopciones/{id}")
    AdopcionDTO buscarAdopcionPorId(@PathVariable Integer id);
    @GetMapping("api/v1/adopciones(usuario/{idUsuario}")
    UsuarioDTO buscarPorUsuario(@PathVariable Integer idUsuario);
    

}
