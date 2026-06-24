package cl.duoc.msprocesoadopcion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.msprocesoadopcion.model.Adopcion;
import cl.duoc.msprocesoadopcion.service.AdopcionService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/adopciones")
@Tag(name = "Adopcion", description = "Operaciones relacionadas con adopciones")
public class AdopcionController {

@Autowired
    private AdopcionService adopcionService;

    @Operation(summary = "Listar todas las adopciones", description = "Obtiene una lista de todas las adopciones registradas en el sistema")    
@GetMapping
public ResponseEntity<List<Adopcion>> listarAdopciones() {
        List<Adopcion> listaAdopciones = adopcionService.listarAdopciones();

        if (listaAdopciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaAdopciones);
    }

    @Operation(summary = "Buscar adopción por ID", description = "Obtiene los detalles de una adopción específica utilizando su ID")    
    @GetMapping("/{id}")
    public ResponseEntity<Adopcion> buscarPorId(@PathVariable Integer id){
        try {
            Adopcion adopcion = adopcionService.buscarAdopcionPorId(id);
            return ResponseEntity.ok(adopcion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Guardar adopción", description = "Crea una nueva adopción en el sistema")    
    @PostMapping
public ResponseEntity<Adopcion> guardarAdopcion(@RequestBody Adopcion adopcion){
    Adopcion nuevaAdopcion = adopcionService.guardarAdopcion(adopcion);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAdopcion);
}


    @Operation(summary = "Eliminar adopción por ID", description = "Elimina una adopción específica utilizando su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Adopcion> eliminarAdopcion(@PathVariable Integer id){
        try {
            adopcionService.eliminarAdopcion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
    