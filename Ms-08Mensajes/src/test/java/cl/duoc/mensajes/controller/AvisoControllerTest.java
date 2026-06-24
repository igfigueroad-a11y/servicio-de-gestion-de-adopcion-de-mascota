package cl.duoc.mensajes.controller;



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
import java.util.Date;
import java.util.List;

import cl.duoc.mensajes.model.Aviso;
import cl.duoc.mensajes.service.AvisoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AvisoController.class)
public class AvisoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvisoService avisoService;

    private Aviso aviso;

    @BeforeEach
    void setUp() {
        aviso = new Aviso();
        aviso.setId(1);
        aviso.setId_usuario_destino(10);
        aviso.setCanalEnvio("EMAIL");
        aviso.setAsunto("Recordatorio");
        aviso.setMensaje("Tu evento fue actualizado");
        aviso.setFechaEnvio(new Date());
        aviso.setTipoEvento("ACTUALIZACION");
        aviso.setId_evento_relacionado(100);
    }

    @Test
    void listarAvisos_retorna200_conDatos() throws Exception {
        List<Aviso> lista = new ArrayList<>();
        lista.add(aviso);

        when(avisoService.listarMensajes()).thenReturn(lista);

        mockMvc.perform(get("/api/v1/avisos"))
                .andExpect(status().isOk());
    }

    @Test
    void listarAvisos_retorna204_sinDatos() throws Exception {
        when(avisoService.listarMensajes()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/avisos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarAvisoPorId_retorna200() throws Exception {
        when(avisoService.buscarAvisoPorId(1)).thenReturn(aviso);

        mockMvc.perform(get("/api/v1/avisos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarAvisoPorId_retorna404_siNoExiste() throws Exception {
        when(avisoService.buscarAvisoPorId(99))
                .thenThrow(new RuntimeException("Aviso no encontrado"));

        mockMvc.perform(get("/api/v1/avisos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void guardarAviso_retorna200() throws Exception {
        when(avisoService.guardarAviso(any(Aviso.class))).thenReturn(aviso);

        mockMvc.perform(post("/api/v1/avisos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "id_usuario_destino": 10,
                          "canalEnvio": "EMAIL",
                          "asunto": "Recordatorio",
                          "mensaje": "Tu evento fue actualizado",
                          "tipoEvento": "ACTUALIZACION",
                          "id_evento_relacionado": 100
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarAviso_retorna204() throws Exception {
        doNothing().when(avisoService).eliminarAviso(1);

        mockMvc.perform(delete("/api/v1/avisos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarAviso_retorna404_siNoExiste() throws Exception {
        doThrow(new RuntimeException("Aviso no encontrado"))
                .when(avisoService).eliminarAviso(99);

        mockMvc.perform(delete("/api/v1/avisos/99"))
                .andExpect(status().isNotFound());
    }
}