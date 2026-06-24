package cl.duoc.msprocesoadopcion.controller;



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

import cl.duoc.msprocesoadopcion.model.Adopcion;
import cl.duoc.msprocesoadopcion.service.AdopcionService;

@WebMvcTest(AdopcionController.class)
public class AdopcionControllerTest {

    @Autowired
    private MockMvc llamadaFalsa;

    @MockBean
    private AdopcionService adopcionService;

    private Adopcion adopcion;

    @BeforeEach
    void setUp() {
        adopcion = new Adopcion();
        adopcion.setId_adopcion(1);
        adopcion.setIdUsuario(10);
        adopcion.setId_mascota(5);
    }

    @Test
    void listarAdopciones_retorna200_conDatos() throws Exception {
        List<Adopcion> lista = new ArrayList<>();
        lista.add(adopcion);

        when(adopcionService.listarAdopciones()).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/adopciones"))
                .andExpect(status().isOk());
    }

    @Test
    void listarAdopciones_retorna204_sinDatos() throws Exception {
        when(adopcionService.listarAdopciones()).thenReturn(Collections.emptyList());

        llamadaFalsa.perform(get("/api/v1/adopciones"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        when(adopcionService.buscarAdopcionPorId(1)).thenReturn(adopcion);

        llamadaFalsa.perform(get("/api/v1/adopciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorId_retorna404_siNoExiste() throws Exception {
        when(adopcionService.buscarAdopcionPorId(99))
                .thenThrow(new RuntimeException("Adopcion no encontrada"));

        llamadaFalsa.perform(get("/api/v1/adopciones/99"))
                .andExpect(status().isNotFound());
    }

@Test
void guardarAdopcion_retorna200() throws Exception {
    when(adopcionService.guardarAdopcion(any(Adopcion.class))).thenReturn(adopcion);

    llamadaFalsa.perform(post("/api/v1/adopciones")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                    {
                      "IdUsuario": 10,
                      "Id_mascota": 5
                    }
                    """))
            .andExpect(status().isCreated());
}

    @Test
    void eliminarAdopcion_retorna204() throws Exception {
        doNothing().when(adopcionService).eliminarAdopcion(1);

        llamadaFalsa.perform(delete("/api/v1/adopciones/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarAdopcion_retorna404_siNoExiste() throws Exception {
        doThrow(new RuntimeException("Adopcion no encontrada"))
                .when(adopcionService).eliminarAdopcion(99);

        llamadaFalsa.perform(delete("/api/v1/adopciones/99"))
                .andExpect(status().isNotFound());
    }
}