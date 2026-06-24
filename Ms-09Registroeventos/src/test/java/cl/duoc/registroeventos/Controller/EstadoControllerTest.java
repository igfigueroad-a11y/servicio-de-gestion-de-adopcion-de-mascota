package cl.duoc.registroeventos.Controller;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import cl.duoc.registroeventos.controller.EstadoController;
import cl.duoc.registroeventos.model.Estado;
import cl.duoc.registroeventos.service.EstadoService;

@WebMvcTest(EstadoController.class)
public class EstadoControllerTest {

    @Autowired
    private MockMvc llamadaFalsa;

    @MockBean
    private EstadoService estadoService;

    private Estado estado;

    @BeforeEach
    void setUp() {
        estado = new Estado();
        estado.setIdMascota(1);
        estado.setEstadoMascota("Pendiente");
    }

    @Test
    void listarEstados_retorna200_conDatos() throws Exception {
        List<Estado> lista = new ArrayList<>();
        lista.add(estado);

        when(estadoService.listarEstados()).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/estados"))
                .andExpect(status().isOk());
    }

    @Test
    void listarEstados_retorna204_sinDatos() throws Exception {
        when(estadoService.listarEstados()).thenReturn(Collections.emptyList());

        llamadaFalsa.perform(get("/api/v1/estados"))
                .andExpect(status().isNoContent());
    }

    @Test
    void guardarEstadoPorId_retorna200() throws Exception {
        when(estadoService.guardarEstadoPorId(1)).thenReturn(estado);

        llamadaFalsa.perform(get("/api/v1/estados/1"))
                .andExpect(status().isOk());
    }

    @Test
    void guardarEstadoPorId_retorna404_siNoExiste() throws Exception {
        when(estadoService.guardarEstadoPorId(99))
                .thenThrow(new RuntimeException("Estado no encontrado"));

        llamadaFalsa.perform(get("/api/v1/estados/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void guardarEstado_retorna200() throws Exception {
        when(estadoService.guardarEstado(any(Estado.class))).thenReturn(estado);

        llamadaFalsa.perform(post("/api/v1/estados")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "nombre": "Pendiente"
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarEstado_retorna204() throws Exception {
        doNothing().when(estadoService).eliminarEstado(1);

        llamadaFalsa.perform(delete("/api/v1/estados/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarEstado_retorna404_siNoExiste() throws Exception {
        doThrow(new RuntimeException("Estado no encontrado"))
                .when(estadoService).eliminarEstado(99);

        llamadaFalsa.perform(delete("/api/v1/estados/99"))
                .andExpect(status().isNotFound());
    }
}