package cl.duoc.registroeventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.registroeventos.model.Estado;
import cl.duoc.registroeventos.service.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping("/api/v1/estados")
@Tag(name = "Estado", description = "Operaciones relacionadas con los estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @Operation(summary = "Listar todos los estados", description = "Obtiene una lista de todos los estados disponibles")
    @GetMapping
    public ResponseEntity<List<Estado>> listarEstados() {
        List<Estado> listaEstados = estadoService.listarEstados();
        if (listaEstados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaEstados);
    }

    @Operation(summary = "Guardar un estado por ID", description = "Guarda un estado en la base de datos utilizando su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Estado> guardarEstadoPorId(@PathVariable Integer id) {
        try {
            Estado estado = estadoService.guardarEstadoPorId(id);
            return ResponseEntity.ok(estado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "GUARDAR UN ESTADO", description = "GUARDA UN ESTADO EN EL SISTEMA")
    @PostMapping
    public ResponseEntity<Estado> guardarEstado(@RequestBody Estado estado) {
        Estado estadoGuardado = estadoService.guardarEstado(estado);
        return ResponseEntity.ok(estadoGuardado);
    }

    @Operation(summary = "ELIMINAR UN ESTADO", description = "ELIMINA UN ESTADO DEL SISTEMA POR SU ID") 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Integer id) {
        try {
            estadoService.eliminarEstado(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
