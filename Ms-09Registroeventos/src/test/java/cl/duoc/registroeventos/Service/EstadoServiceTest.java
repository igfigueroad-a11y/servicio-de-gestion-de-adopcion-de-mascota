package cl.duoc.registroeventos.Service;


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

import cl.duoc.registroeventos.model.Estado;
import cl.duoc.registroeventos.repository.EstadoRepository;
import cl.duoc.registroeventos.service.EstadoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EstadoServiceTest {

    @Mock
    private EstadoRepository estadoRepository;

    @InjectMocks
    private EstadoService estadoService;

    private Estado estado;

    @BeforeEach
    void setUp() {
        estado = new Estado();
        estado.setId(1);
        estado.setIdMascota(10);
        estado.setEstadoMascota("En observación");
        estado.setFechaEstado(new Date());
        estado.setMotivoCambio("Ingreso al sistema");
    }

    @Test
    void listarEstados_debeRetornarLista() {
        when(estadoRepository.findAll()).thenReturn(Arrays.asList(estado));

        List<Estado> resultado = estadoService.listarEstados();

        assertEquals(1, resultado.size());
        assertEquals("En observación", resultado.get(0).getEstadoMascota());
    }

    @Test
    void guardarEstado_debeRetornarEstadoGuardado() {
        when(estadoRepository.save(any(Estado.class))).thenReturn(estado);

        Estado resultado = estadoService.guardarEstado(estado);

        assertEquals(1, resultado.getId());
        assertEquals(10, resultado.getIdMascota());
    }

    @Test
    void guardarEstadoPorId_debeRetornarEstadoSiExiste() {
        when(estadoRepository.findById(1)).thenReturn(Optional.of(estado));

        Estado resultado = estadoService.guardarEstadoPorId(1);

        assertEquals(1, resultado.getId());
        assertEquals("En observación", resultado.getEstadoMascota());
    }

    @Test
    void guardarEstadoPorId_debeLanzarExcepcionSiNoExiste() {
        when(estadoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> estadoService.guardarEstadoPorId(99));

        assertEquals("Estado no encontrado con id: 99", ex.getMessage());
    }

    @Test
    void eliminarEstado_debeEjecutarDeleteById() {
        doNothing().when(estadoRepository).deleteById(1);

        estadoService.eliminarEstado(1);

        verify(estadoRepository).deleteById(1);
    }
}