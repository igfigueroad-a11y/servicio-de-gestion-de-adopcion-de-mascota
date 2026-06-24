package cl.duoc.mscontrolpostadopcion.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.mscontrolpostadopcion.model.Visita;
import cl.duoc.mscontrolpostadopcion.repository.VisitaRepository;

@ExtendWith(MockitoExtension.class)
public class VisitaServiceTest {

    @Mock
    private VisitaRepository visitaRepository;

    @InjectMocks
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
    void listarVisitas_retornaLista() {
        when(visitaRepository.findAll()).thenReturn(Arrays.asList(visita));

        List<Visita> resultado = visitaService.listarVisitas();

        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getId_adopcion());
    }

    @Test
    void listarVisitas_retornaListaVacia() {
        when(visitaRepository.findAll()).thenReturn(Collections.emptyList());

        List<Visita> resultado = visitaService.listarVisitas();

        assertEquals(0, resultado.size());
    }

    @Test
    void guardarVisita_retornaVisitaGuardada() {
        when(visitaRepository.save(visita)).thenReturn(visita);

        Visita resultado = visitaService.guardarVisita(visita);

        assertEquals(1, resultado.getId_visita());
        assertEquals(10, resultado.getId_adopcion());
        assertEquals("Visita en buen estado", resultado.getDescripcion_visita());
        verify(visitaRepository).save(visita);
    }

    @Test
    void buscarVisitaPorId_retornaVisita() {
        when(visitaRepository.findById(1)).thenReturn(Optional.of(visita));

        Visita resultado = visitaService.buscarVisitaPorId(1);

        assertEquals(1, resultado.getId_visita());
        assertEquals(10, resultado.getId_adopcion());
    }

    @Test
    void buscarVisitaPorId_lanzaError_siNoExiste() {
        when(visitaRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> visitaService.buscarVisitaPorId(99));

        assertEquals("Visita no encontrada", ex.getMessage());
    }

    @Test
    void eliminarVisita_eliminaCorrectamente() {
        when(visitaRepository.existsById(1)).thenReturn(true);
        doNothing().when(visitaRepository).deleteById(1);

        visitaService.eliminarVisita(1);

        verify(visitaRepository).deleteById(1);
    }

    @Test
    void eliminarVisita_lanzaError_siNoExiste() {
        when(visitaRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> visitaService.eliminarVisita(99));

        assertEquals("Visita no encontrada", ex.getMessage());
    }
}