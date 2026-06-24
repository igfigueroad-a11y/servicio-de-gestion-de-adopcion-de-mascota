package cl.duoc.mscontrolpostadopcion.controller;



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

import cl.duoc.mscontrolpostadopcion.model.Visita;
import cl.duoc.mscontrolpostadopcion.service.VisitaService;

@WebMvcTest(VisitaController.class)
public class VisitaControllerTest {

    @Autowired
    private MockMvc llamadaFalsa;

    @MockBean
    private VisitaService visitaService;

    private Visita visita;

    @BeforeEach
    void setUp() {
        visita = new Visita();
        visita.setId_visita(1);
        visita.setId_adopcion(10);
        visita.setDescripcion_visita("Visita en buen estado");
    }

    @Test
    void listarVisitas_retorna200_conDatos() throws Exception {
        List<Visita> lista = new ArrayList<>();
        lista.add(visita);

        when(visitaService.listarVisitas()).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/visitas"))
                .andExpect(status().isOk());
    }

    @Test
    void listarVisitas_retorna204_sinDatos() throws Exception {
        when(visitaService.listarVisitas()).thenReturn(Collections.emptyList());

        llamadaFalsa.perform(get("/api/v1/visitas"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarVisitaPorId_retorna200() throws Exception {
        when(visitaService.buscarVisitaPorId(1)).thenReturn(visita);

        llamadaFalsa.perform(get("/api/v1/visitas/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarVisitaPorId_retorna404_siNoExiste() throws Exception {
        when(visitaService.buscarVisitaPorId(99))
                .thenThrow(new RuntimeException("Visita no encontrada"));

        llamadaFalsa.perform(get("/api/v1/visitas/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void guardarVisita_retorna200() throws Exception {
        when(visitaService.guardarVisita(any(Visita.class))).thenReturn(visita);

        llamadaFalsa.perform(post("/api/v1/visitas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "idAdopcion": 10,
                          "comentario": "Visita en buen estado"
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarVisita_retorna204() throws Exception {
        doNothing().when(visitaService).eliminarVisita(1);

        llamadaFalsa.perform(delete("/api/v1/visitas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarVisita_retorna404_siNoExiste() throws Exception {
        doThrow(new RuntimeException("Visita no encontrada"))
                .when(visitaService).eliminarVisita(99);

        llamadaFalsa.perform(delete("/api/v1/visitas/99"))
                .andExpect(status().isNotFound());
    }
}