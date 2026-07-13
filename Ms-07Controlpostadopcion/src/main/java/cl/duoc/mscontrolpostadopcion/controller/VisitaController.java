package cl.duoc.mscontrolpostadopcion.controller;

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
import cl.duoc.mscontrolpostadopcion.model.Visita;
import cl.duoc.mscontrolpostadopcion.service.VisitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/controlpostadopcion")
@Tag(name = "Visita", description = "Controlador para gestionar las visitas de control post adopción")
public class VisitaController {

    @Autowired
private VisitaService visitaService;  


@Operation(summary = "Listar todas las visitas", description = "Obtiene una lista de todas las visitas de control post adopción")   
@GetMapping
public ResponseEntity<List<Visita>> listarVisitas() {
    List<Visita> visitas = visitaService.listarVisitas();

    if (visitas.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(visitas);
}

@Operation(summary = "Buscar visita por ID", description = "Obtiene una visita específica por su ID")   
@GetMapping("/{id}")
public ResponseEntity<Visita> buscarVisitaPorId(@PathVariable Integer id) {
    try {
        Visita visita = visitaService.buscarVisitaPorId(id);
        return ResponseEntity.ok(visita);
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
}

@Operation(summary = "Guardar visita", description = "Crea una nueva visita de control post adopción")   
@PostMapping
public ResponseEntity<Visita> guardarVisita(@RequestBody Visita visita) {
    Visita nuevaVisita = visitaService.guardarVisita(visita);
    return ResponseEntity.ok(nuevaVisita);

}

@Operation(summary = "Eliminar visita", description = "Elimina una visita específica por su ID")   
@DeleteMapping("/{id}")
public ResponseEntity<Visita> eliminarVisita(@PathVariable Integer id) {
    try {
        visitaService.eliminarVisita(id);
        return ResponseEntity.noContent().build();
    } catch (Exception e) {
        return ResponseEntity.notFound().build();
    }
}
}