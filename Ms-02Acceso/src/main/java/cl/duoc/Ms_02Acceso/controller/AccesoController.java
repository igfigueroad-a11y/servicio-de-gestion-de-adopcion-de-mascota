package cl.duoc.Ms_02Acceso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.Ms_02Acceso.dto.AccesoDto;
import cl.duoc.Ms_02Acceso.model.Acceso;
import cl.duoc.Ms_02Acceso.service.AccesoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1/acceso")
@Tag(name = "Acceso", description = "API para la gestión de cuentas de acceso")
public class AccesoController {

    @Autowired
    private AccesoService acs;
    @Operation(summary = "Listar todas las cuentas de acceso", description = "Obtiene una lista de todas las cuentas de acceso registradas en el sistema.")
    @GetMapping
    public ResponseEntity<List<AccesoDto>> listarCuentas() {
        return ResponseEntity.ok(acs.listarCuentas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cuenta de acceso por ID", description = "Obtiene los detalles de una cuenta de acceso específica por su ID.")
    public ResponseEntity<AccesoDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(acs.buscarPorId(id));
    }

    @GetMapping("/usuario/{nombreUsuario}")
    @Operation(summary = "Buscar cuenta de acceso por nombre de usuario", description = "Obtiene los detalles de una cuenta de acceso específica por su nombre de usuario.")
    public ResponseEntity<AccesoDto> buscarPorNombreUsuario(@PathVariable String nombreUsuario) {
        return ResponseEntity.ok(acs.buscarPorNombreUsuario(nombreUsuario));
    }

    @PostMapping
    @Operation(summary = "Crear nueva cuenta de acceso", description = "Crea una nueva cuenta de acceso en el sistema.")
    public ResponseEntity<AccesoDto> crearAcceso(@RequestBody Acceso ac) {
        return ResponseEntity.status(HttpStatus.CREATED).body(acs.crearAcceso(ac));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cuenta de acceso", description = "Actualiza los datos de una cuenta de acceso específica por su ID.")
    public ResponseEntity<AccesoDto> actualizarAcceso(@PathVariable Integer id, @RequestBody Acceso ac) {
        return ResponseEntity.ok(acs.actualizarAccesoDto(id, ac));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cuenta de acceso", description = "Elimina una cuenta de acceso específica por su ID.")
    public ResponseEntity<String> eliminarAcceso(@PathVariable Integer id) {
        acs.eliminarAcceso(id);
        return ResponseEntity.ok("Cuenta eliminada correctamente");
    }
}