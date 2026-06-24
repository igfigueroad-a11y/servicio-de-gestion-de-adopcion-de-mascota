package cl.duoc.ms_mascotas.controller;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import cl.duoc.ms_mascotas.dto.MascotaDto;
import cl.duoc.ms_mascotas.model.Mascota;
import cl.duoc.ms_mascotas.service.MascotaService;
@RequestMapping("/api/v1/mascotas")
@RestController
@Tag(name = "Mascotas", description = "API para la gestión de mascotas")
public class MascotaController {
@Autowired
   private MascotaService ms;

   @Operation(summary = "Listar todas las mascotas", description = "Obtiene una lista de todas las mascotas registradas en el sistema")
@GetMapping
public ResponseEntity<List<MascotaDto>> ListarMascotas() {
    return ResponseEntity.ok(ms.ListarMascotas());
   }
    @Operation(summary = "Guardar mascota", description = "Guarda una nueva mascota en el sistema")
@PostMapping        
public ResponseEntity<MascotaDto> GuardarMascota(@RequestBody Mascota mascota) {
    return ResponseEntity.status(HttpStatus.CREATED).body(ms.GuardarMascota(mascota));
    }

@Operation(summary = "Buscar mascota por ID", description = "Obtiene los detalles de una mascota específica por su ID")
@GetMapping("/{id}")
public ResponseEntity<MascotaDto> BuscarPorId(@PathVariable Integer id){
    return ResponseEntity.ok(ms.BuscarPorId(id));
}

@Operation(summary = "Filtrar mascotas por especie", description = "Obtiene una lista de mascotas filtradas por su especie")
@GetMapping("/especie/{especie}")
public ResponseEntity<List<MascotaDto>> FiltrarPorEspecie(@PathVariable String especie){
    return ResponseEntity.ok(ms.FiltrarPorEspecie(especie));
}

@Operation(summary = "Eliminar mascota", description = "Elimina una mascota específica del sistema")
@DeleteMapping("/{id}")
public ResponseEntity<Void> EliminarMascota(@PathVariable Integer id){
    ms.EliminarMascota(id);
    return ResponseEntity.noContent().build();
}
}
