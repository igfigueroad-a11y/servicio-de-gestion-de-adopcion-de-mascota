package cl.duoc.msusarios.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import cl.duoc.msusarios.dto.UsuarioDto;
import cl.duoc.msusarios.service.UsuarioService;

@WebMvcTest(UsuarioController.class)
public class UsuariosControllerTest {

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private MockMvc llamadafalsa;

    private UsuarioDto usuarioejem;

    @BeforeEach
    void setUp() {
        usuarioejem = new UsuarioDto();
        usuarioejem.setIdUsuario(1);
        usuarioejem.setNombreCompleto("ignacio figueroa");
        usuarioejem.setCorreoElectronico("ignacio@correo.com");
    }

    @Test
    void listar_retorna200() throws Exception {
        List<UsuarioDto> lista = new ArrayList<>();
        lista.add(usuarioejem);
        when(usuarioService.buscarUsuarios()).thenReturn(lista);

        llamadafalsa.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        when(usuarioService.buscarUsuarioPorId(1)).thenReturn(usuarioejem);

        llamadafalsa.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorId_retorna500_noEncontrado() throws Exception {
        when(usuarioService.buscarUsuarioPorId(99))
                .thenThrow(new RuntimeException("Usuario no encontrado"));

        llamadafalsa.perform(get("/api/v1/usuarios/99"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void buscarPorCorreo_retorna200() throws Exception {
        when(usuarioService.buscarPorCorreo("ignacio@correo.com"))
                .thenReturn(usuarioejem);

        llamadafalsa.perform(get("/api/v1/usuarios/correo/ignacio@correo.com"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorCorreo_retorna500_noEncontrado() throws Exception {
        when(usuarioService.buscarPorCorreo("noexiste@correo.com"))
                .thenThrow(new RuntimeException("Usuario no encontrado"));

        llamadafalsa.perform(get("/api/v1/usuarios/correo/noexiste@correo.com"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void crearUsuario_retorna201() throws Exception {
        when(usuarioService.crearUsuario(usuarioejem)).thenReturn(usuarioejem);

        llamadafalsa.perform(post("/api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idUsuario\":1,\"nombreCompleto\":\"ignacio figueroa\",\"correoElectronico\":\"ignacio@correo.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizarUsuario_retorna200() throws Exception {
        when(usuarioService.actualizarUsuario(1, usuarioejem)).thenReturn(usuarioejem);

        llamadafalsa.perform(put("/api/v1/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idUsuario\":1,\"nombreCompleto\":\"ignacio figueroa\",\"correoElectronico\":\"ignacio@correo.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarUsuario_retorna500_noEncontrado() throws Exception {
        when(usuarioService.actualizarUsuario(99, usuarioejem))
                .thenThrow(new RuntimeException("Usuario no encontrado"));

        llamadafalsa.perform(put("/api/v1/usuarios/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idUsuario\":1,\"nombreCompleto\":\"ignacio figueroa\",\"correoElectronico\":\"ignacio@correo.com\"}"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void eliminarUsuario_retorna200() throws Exception {
        doNothing().when(usuarioService).eliminarUsuario(1);

        llamadafalsa.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarUsuario_retorna500_noEncontrado() throws Exception {
        doThrow(new RuntimeException("Usuario no encontrado"))
                .when(usuarioService).eliminarUsuario(99);

        llamadafalsa.perform(delete("/api/v1/usuarios/99"))
                .andExpect(status().isInternalServerError());
    }
}