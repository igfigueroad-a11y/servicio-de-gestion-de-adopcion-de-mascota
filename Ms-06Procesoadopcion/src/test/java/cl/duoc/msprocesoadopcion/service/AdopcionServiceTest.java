package cl.duoc.msprocesoadopcion.service;



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

import cl.duoc.msprocesoadopcion.model.Adopcion;
import cl.duoc.msprocesoadopcion.repository.AdopcionRepository;

@ExtendWith(MockitoExtension.class)
public class AdopcionServiceTest {

    @Mock
    private AdopcionRepository adopcionRepository;

    @InjectMocks
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
    void listarAdopciones_retornaLista() {
        when(adopcionRepository.findAll()).thenReturn(Arrays.asList(adopcion));

        List<Adopcion> resultado = adopcionService.listarAdopciones();

        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getIdUsuario());
        assertEquals(5, resultado.get(0).getId_mascota());
    }

    @Test
    void listarAdopciones_retornaListaVacia() {
        when(adopcionRepository.findAll()).thenReturn(Collections.emptyList());

        List<Adopcion> resultado = adopcionService.listarAdopciones();

        assertEquals(0, resultado.size());
    }

    @Test
    void guardarAdopcion_retornaAdopcionGuardada() {
        when(adopcionRepository.save(adopcion)).thenReturn(adopcion);

        Adopcion resultado = adopcionService.guardarAdopcion(adopcion);

        assertEquals(1, resultado.getId_adopcion());
        assertEquals(10, resultado.getIdUsuario());
        assertEquals(5, resultado.getId_mascota());
        verify(adopcionRepository).save(adopcion);
    }

    @Test
    void buscarAdopcionPorId_retornaAdopcion() {
        when(adopcionRepository.findById(1)).thenReturn(Optional.of(adopcion));

        Adopcion resultado = adopcionService.buscarAdopcionPorId(1);

        assertEquals(1, resultado.getId_adopcion());
        assertEquals(10, resultado.getIdUsuario());
    }

    @Test
    void buscarAdopcionPorId_lanzaError_siNoExiste() {
        when(adopcionRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> adopcionService.buscarAdopcionPorId(99));

        assertEquals("Adopción no encontrada", ex.getMessage());
    }

  @Test
void eliminarAdopcion_eliminaCorrectamente() {
    when(adopcionRepository.existsById(1)).thenReturn(true);
    doNothing().when(adopcionRepository).deleteById(1);

    adopcionService.eliminarAdopcion(1);

    verify(adopcionRepository).deleteById(1);
}

    @Test
    void eliminarAdopcion_lanzaError_siNoExiste() {
        when(adopcionRepository.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> adopcionService.eliminarAdopcion(99));

        assertEquals("Adopción no encontrada", ex.getMessage());
    }
}