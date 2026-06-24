package cl.duoc.mensajes.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import cl.duoc.mensajes.service.AvisoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.mensajes.model.Aviso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/api/v1/avisos")
@Tag(name = "Aviso Controller", description = "Controlador para gestionar avisos")
public class AvisoController {

    @Autowired
    private AvisoService avisoService;

    @Operation(summary = "Listar todos los avisos", description = "Obtiene una lista de todos los avisos disponibles")
    @GetMapping
    public ResponseEntity<List<Aviso>> listarAvisos() {
        List<Aviso> ListaAvisos = avisoService.listarMensajes();
        if(ListaAvisos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ListaAvisos);
    }
    @Operation(summary = "Buscar aviso por ID", description = "Obtiene un aviso específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Aviso> BuscarAvisoPorId(@PathVariable Integer id) {
        try {
            Aviso aviso = avisoService.buscarAvisoPorId(id);
            return ResponseEntity.ok(aviso);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Guardar aviso", description = "Crea un nuevo aviso")
 @PostMapping
public ResponseEntity<Aviso> guardarAviso(@RequestBody Aviso aviso) {
    Aviso nuevoAviso = avisoService.guardarAviso(aviso);
    return ResponseEntity.ok(nuevoAviso);
}


    @Operation(summary = "Eliminar aviso por ID", description = "Elimina un aviso específico por su ID")
@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarAviso(@PathVariable Integer id) {
    try {
        avisoService.eliminarAviso(id);
        return ResponseEntity.noContent().build();
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
}


}
