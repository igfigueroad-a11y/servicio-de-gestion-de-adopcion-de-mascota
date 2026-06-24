package cl.duoc.ms_04tipomascota.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.ms_04tipomascota.dto.TipoMascotaDto;
import cl.duoc.ms_04tipomascota.model.TipoMascota;
import cl.duoc.ms_04tipomascota.service.TipoMascotaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/tipomascota")
@Tag(name = "TipoMascota", description = "Operaciones relacionadas con el tipo de mascota")
public class TipoMascotaController {

    @Autowired
    private TipoMascotaService tpms;

    @Operation(summary = "Listar tipos de mascota", description = "Obtiene una lista de todos los tipos de mascota")
    @GetMapping
    public ResponseEntity<List<TipoMascotaDto>> ListarTipos() {
        return ResponseEntity.ok(tpms.ListarTipoMascota());
    }

    @Operation(summary = "Buscar tipo de mascota por ID", description = "Obtiene un tipo de mascota por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<TipoMascotaDto> BuscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tpms.BuscarPorId(id));
    }

    @Operation(summary = "Guardar tipo de mascota", description = "Crea un nuevo tipo de mascota")
    @PostMapping
    public ResponseEntity<TipoMascotaDto> GuardarTipo(@RequestBody TipoMascota tipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tpms.GuardarTipo(tipo));
    }

    @Operation(summary = "Actualizar tipo de mascota", description = "Actualiza un tipo de mascota existente")
    @PutMapping("/{id}")
    public ResponseEntity<TipoMascotaDto> ActualizarTipo(@PathVariable Integer id, @RequestBody TipoMascota tipo) {
        return ResponseEntity.ok(tpms.ActualizarTipo(id, tipo));
    }

    @Operation(summary = "Eliminar tipo de mascota", description = "Elimina un tipo de mascota existente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> EliminarTipo(@PathVariable Integer id) {
        tpms.EliminarTipo(id);
        return ResponseEntity.noContent().build();
    }
}