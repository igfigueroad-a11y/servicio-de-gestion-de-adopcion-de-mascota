package cl.duoc.msprocesoadopcion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.duoc.msprocesoadopcion.dto.PostulacionDTO;

@FeignClient(name = "ms-05postulacion", url="http://localhost:8085")
public interface PostulacionClient {
    @GetMapping("api/v1postulaciones/{id}")
    Object buscarPostulacionPorId(@PathVariable Integer id);

    @PutMapping("/api/v1/postulaciones/{id}/estado")

    PostulacionDTO cambiarEstado(@PathVariable Integer id, @RequestParam String estado);






}
