package cl.duoc.estadisticas.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import cl.duoc.estadisticas.model.Reporte;
import cl.duoc.estadisticas.repository.ReporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
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
    void listarReportes_debeRetornarLista() {
        when(reporteRepository.findAll()).thenReturn(Arrays.asList(reporte));

        List<Reporte> resultado = reporteService.listarReportes();

        assertEquals(1, resultado.size());
        assertEquals("Reporte mensual", resultado.get(0).getNombre());
    }

    @Test
    void guardarReporte_debeRetornarReporteGuardado() {
        when(reporteRepository.save(any(Reporte.class))).thenReturn(reporte);

        Reporte resultado = reporteService.guardarReporte(reporte);

        assertEquals(1, resultado.getId_reporte());
        assertEquals("GENERAL", resultado.getTipoReporte());
    }

    @Test
    void buscarReportePorId_debeRetornarReporteSiExiste() {
        when(reporteRepository.findById(1)).thenReturn(Optional.of(reporte));

        Reporte resultado = reporteService.buscarReportePorId(1);

        assertEquals(1, resultado.getId_reporte());
        assertEquals("OK", resultado.getResultado());
    }

    @Test
    void buscarReportePorId_debeLanzarExcepcionSiNoExiste() {
        when(reporteRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> reporteService.buscarReportePorId(99));

        assertEquals("Reporte no encontrado", ex.getMessage());
    }

    @Test
    void eliminarReporte_debeEjecutarDeleteById() {
        doNothing().when(reporteRepository).deleteById(1);

        reporteService.eliminarReporte(1);

        verify(reporteRepository).deleteById(1);
    }
}