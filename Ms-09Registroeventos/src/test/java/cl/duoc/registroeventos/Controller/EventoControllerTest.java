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

import cl.duoc.registroeventos.controller.EventoController;
import cl.duoc.registroeventos.model.Evento;
import cl.duoc.registroeventos.service.EventoService;

@WebMvcTest(EventoController.class)
public class EventoControllerTest {

    @Autowired
    private MockMvc llamadaFalsa;

    @MockBean
    private EventoService eventoService;

    private Evento evento;

    @BeforeEach
    void setUp() {
        evento = new Evento();
        evento.setId_evento(1);
        evento.setDescripcionEvento("Evento registrado");
    }

    @Test
    void listarEventos_retorna200_conDatos() throws Exception {
        List<Evento> lista = new ArrayList<>();
        lista.add(evento);

        when(eventoService.listarEventos()).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/eventos"))
                .andExpect(status().isOk());
    }

    @Test
    void listarEventos_retorna204_sinDatos() throws Exception {
        when(eventoService.listarEventos()).thenReturn(Collections.emptyList());

        llamadaFalsa.perform(get("/api/v1/eventos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void obtenerEventoPorId_retorna200() throws Exception {
        when(eventoService.guardarEventoPorId(1)).thenReturn(evento);

        llamadaFalsa.perform(get("/api/v1/eventos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerEventoPorId_retorna404_siNoExiste() throws Exception {
        when(eventoService.guardarEventoPorId(99))
                .thenThrow(new RuntimeException("Evento no encontrado"));

        llamadaFalsa.perform(get("/api/v1/eventos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void guardarEvento_retorna200() throws Exception {
        when(eventoService.guardarEvento(any(Evento.class))).thenReturn(evento);

        llamadaFalsa.perform(post("/api/v1/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "descripcion": "Evento registrado"
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarEvento_retorna204() throws Exception {
        doNothing().when(eventoService).eliminarEvento(1);

        llamadaFalsa.perform(delete("/api/v1/eventos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarEvento_retorna404_siNoExiste() throws Exception {
        doThrow(new RuntimeException("Evento no encontrado"))
                .when(eventoService).eliminarEvento(99);

        llamadaFalsa.perform(delete("/api/v1/eventos/99"))
                .andExpect(status().isNotFound());
    }
}