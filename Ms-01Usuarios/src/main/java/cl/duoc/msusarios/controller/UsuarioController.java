package cl.duoc.msusarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.msusarios.dto.UsuarioDto;
import cl.duoc.msusarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag (name = "usuarios ")
public class UsuarioController {
    @Autowired
private UsuarioService u;

@Operation(summary = "LISTAR USUARIOS", description = "OTORGA UN LISTA DE USUARIOS")
@GetMapping
public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
    return ResponseEntity.ok(u.buscarUsuarios());
    }

@Operation(summary = "BUSCAR UN USUARIO POR ID",description = "RETORNA UN USUARIO SEGUN SU ID CORRESPONDIENTE")
@GetMapping("/{id}")
public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Integer id) {
    return ResponseEntity.ok(u.buscarUsuarioPorId(id));
    }

@Operation(summary = "BUSCAR UN USUARIO POR CORREO", description = "RETORNA UN USUARIO SEGUN SU CORREO CORRESPONDIENTE")
@GetMapping("/correo/{correo}")
public ResponseEntity<UsuarioDto> buscarPorCorreo(@PathVariable String correo) {
    return ResponseEntity.ok(u.buscarPorCorreo(correo));
    }

@Operation(summary = "CREAR UN USUARIO", description = "CREA UN NUEVO USUARIO")
@PostMapping
public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody UsuarioDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(u.crearUsuario(dto));
    }
@Operation(summary = "ACTUALIZAR UN USUARIO", description = "ACTUALIZA LOS DATOS DE UN USUARIO EXISTENTE SEGUN SU ID")
@PutMapping("/{id}")
public ResponseEntity<UsuarioDto> actualizarUsuario(@PathVariable   Integer id, @RequestBody UsuarioDto dto) {
    return ResponseEntity.ok(u.actualizarUsuario(id, dto));
    }
@Operation(summary = "ELIMINAR USUARIO", description = "ELIMINARA UN USUARIO SEGUN SU ID CORRESPONDIENTE")
@DeleteMapping("/{id}")
public ResponseEntity<String> eliminarUsuario(@PathVariable Integer id) {
    u.eliminarUsuario(id);
    return ResponseEntity.ok("Usuario eliminado correctamente");
    }

  @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleError(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        
}
}