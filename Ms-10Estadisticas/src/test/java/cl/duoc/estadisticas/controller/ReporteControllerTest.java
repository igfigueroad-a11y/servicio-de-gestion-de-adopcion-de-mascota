package cl.duoc.estadisticas.controller;
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

import cl.duoc.estadisticas.model.Reporte;
import cl.duoc.estadisticas.service.ReporteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReporteController.class)
public class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteService reporteService;

    private Reporte reporte;

    @BeforeEach
    void setUp() {
        reporte = new Reporte();
        reporte.setId_reporte(1);
        reporte.setIdUsuario(2);
        reporte.setIdPostulacion(3);
        reporte.setId_mascota(4);
        reporte.setId_adopcion(5);
        reporte.setId_visita(6);
        reporte.setId_evento(7);
        reporte.setNombre("Reporte mensual");
        reporte.setTipoReporte("GENERAL");
        reporte.setFechaCreacion(new Date());
        reporte.setFInicio(new Date());
        reporte.setFFin(new Date());
        reporte.setResultado("OK");
    }

    @Test
    void listarReportes_retorna200_conDatos() throws Exception {
        List<Reporte> lista = new ArrayList<>();
        lista.add(reporte);

        when(reporteService.listarReportes()).thenReturn(lista);

        mockMvc.perform(get("/reportes"))
                .andExpect(status().isOk());
    }

    @Test
    void listarReportes_retorna204_sinDatos() throws Exception {
        when(reporteService.listarReportes()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/reportes"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarReportePorId_retorna200() throws Exception {
        when(reporteService.buscarReportePorId(1)).thenReturn(reporte);

        mockMvc.perform(get("/reportes/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarReportePorId_retorna404_siNoExiste() throws Exception {
        when(reporteService.buscarReportePorId(99))
                .thenThrow(new RuntimeException("Reporte no encontrado"));

        mockMvc.perform(get("/reportes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void guardarReporte_retorna200() throws Exception {
        when(reporteService.guardarReporte(any(Reporte.class))).thenReturn(reporte);

        mockMvc.perform(post("/reportes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "idUsuario": 2,
                          "idPostulacion": 3,
                          "id_mascota": 4,
                          "id_adopcion": 5,
                          "id_visita": 6,
                          "id_evento": 7,
                          "nombre": "Reporte mensual",
                          "tipoReporte": "GENERAL",
                          "resultado": "OK"
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarReporte_retorna204() throws Exception {
        doNothing().when(reporteService).eliminarReporte(1);

        mockMvc.perform(delete("/reportes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarReporte_retorna404_siNoExiste() throws Exception {
        doThrow(new RuntimeException("Reporte no encontrado"))
                .when(reporteService).eliminarReporte(99);

        mockMvc.perform(delete("/reportes/99"))
                .andExpect(status().isNotFound());
    }
}