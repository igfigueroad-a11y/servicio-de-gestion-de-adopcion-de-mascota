package cl.duoc.ms_05postulacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import cl.duoc.ms_05postulacion.dto.PostulacionDto;
import cl.duoc.ms_05postulacion.model.Postulacion;
import cl.duoc.ms_05postulacion.service.PostulacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/api/v1/postulaciones")
@Tag(name = "Postulación", description = "Controlador para la gestión de postulaciones")
public class PostulacionController {
  @Autowired
    private PostulacionService ps;

    @Operation(summary = "Listar todas las postulaciones", description = "Obtiene una lista de todas las postulaciones registradas en el sistema.")
    @GetMapping
public ResponseEntity<List<PostulacionDto>> ListarPostulaciones() {
    return ResponseEntity.ok(ps.ListarPostulaciones());
    }

    @Operation(summary = "Buscar postulación por ID", description = "Obtiene los detalles de una postulación específica por su ID.")
    @GetMapping("/{id}")
public ResponseEntity<PostulacionDto> BuscarPorId(@PathVariable Integer id) {
    return ResponseEntity.ok(ps.BuscarPorId(id));
    }

    @Operation(summary = "Buscar postulaciones por usuario", description = "Obtiene una lista de postulaciones asociadas a un usuario específico.")
    @GetMapping("/usuario/{idUsuario}")
public ResponseEntity<List<PostulacionDto>> BuscarPorUsuario(@PathVariable Integer idUsuario) {
    return ResponseEntity.ok(ps.BuscarPorUsuario(idUsuario));
    }

    @Operation(summary = "Buscar postulaciones por estado", description = "Obtiene una lista de postulaciones que coinciden con un estado específico.")
    @GetMapping("/estado/{estado}")
public ResponseEntity<List<PostulacionDto>> BuscarPorEstado(@PathVariable String estado) {
    return ResponseEntity.ok(ps.BuscarPorEstado(estado));
    }

    @Operation(summary = "Crear nueva postulación", description = "Crea una nueva postulación en el sistema.")
    @PostMapping
public ResponseEntity<PostulacionDto> GuardarPostulacion(@RequestBody Postulacion postulacion) {
    return ResponseEntity.status(HttpStatus.CREATED).body(ps.GuardarPostulacion(postulacion));
    }

    @Operation(summary = "Cambiar estado de postulación", description = "Actualiza el estado de una postulación específica.")
    @PutMapping("/{id}/estado")
public ResponseEntity<PostulacionDto> CambiarEstado(@PathVariable Integer id,@RequestParam String estado) {
    return ResponseEntity.ok(ps.CambiarEstado(id, estado));
    }

    @Operation(summary = "Eliminar postulación", description = "Elimina una postulación específica del sistema.")
    @DeleteMapping("/{id}")
public ResponseEntity<Void> EliminarPostulacion(@PathVariable Integer id) {
    ps.EliminarPostulacion(id);
    return ResponseEntity.noContent().build();
    }
}
