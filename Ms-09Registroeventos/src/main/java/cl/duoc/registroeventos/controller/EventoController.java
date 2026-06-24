package cl.duoc.registroeventos.controller;
import cl.duoc.registroeventos.model.Evento;
import cl.duoc.registroeventos.service.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@RestController
@RequestMapping("/api/v1/eventos")
@Tag(name = "Eventos", description = "Controlador para gestionar eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Operation(summary = "Listar todos los eventos", description = "Obtiene una lista de todos los eventos registrados")
    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos() {
        List<Evento> listaEventos = eventoService.listarEventos();
        if (listaEventos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaEventos);
    }

    @Operation(summary = "Obtener evento por ID", description = "Obtiene los detalles de un evento específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Integer id) {
        try {
            Evento evento = eventoService.guardarEventoPorId(id);
            return ResponseEntity.ok(evento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Guardar evento", description = "Crea un nuevo evento")
    @PostMapping
    public ResponseEntity<Evento> guardarEvento(@RequestBody Evento evento) {
        Evento eventoGuardado = eventoService.guardarEvento(evento);
        return ResponseEntity.ok(eventoGuardado);
    }

    @Operation(summary = "Eliminar evento", description = "Elimina un evento específico por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Integer id) {
        try {
            eventoService.eliminarEvento(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

