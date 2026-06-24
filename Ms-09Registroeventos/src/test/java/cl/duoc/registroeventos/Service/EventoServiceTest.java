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

import cl.duoc.registroeventos.model.Evento;
import cl.duoc.registroeventos.repository.EventoRepository;
import cl.duoc.registroeventos.service.EventoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    private Evento evento;

    @BeforeEach
    void setUp() {
        evento = new Evento();
        evento.setId_evento(1);
        evento.setIdAdopcion(2);
        evento.setIdUsuario(3);
        evento.setId_mascota(4);
        evento.setTipoEvento("Adopción");
        evento.setDescripcionEvento("Se registró una adopción");
        evento.setFechaEvento(new Date());
    }

    @Test
    void listarEventos_debeRetornarLista() {
        when(eventoRepository.findAll()).thenReturn(Arrays.asList(evento));

        List<Evento> resultado = eventoService.listarEventos();

        assertEquals(1, resultado.size());
        assertEquals("Adopción", resultado.get(0).getTipoEvento());
    }

    @Test
    void guardarEvento_debeRetornarEventoGuardado() {
        when(eventoRepository.save(any(Evento.class))).thenReturn(evento);

        Evento resultado = eventoService.guardarEvento(evento);

        assertEquals(1, resultado.getId_evento());
        assertEquals("Se registró una adopción", resultado.getDescripcionEvento());
    }

    @Test
    void guardarEventoPorId_debeRetornarEventoSiExiste() {
        when(eventoRepository.findById(1)).thenReturn(Optional.of(evento));

        Evento resultado = eventoService.guardarEventoPorId(1);

        assertEquals(1, resultado.getId_evento());
        assertEquals("Adopción", resultado.getTipoEvento());
    }

    @Test
    void guardarEventoPorId_debeLanzarExcepcionSiNoExiste() {
        when(eventoRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> eventoService.guardarEventoPorId(99));

        assertEquals("Evento no encontrado con id: 99", ex.getMessage());
    }

    @Test
    void eliminarEvento_debeEjecutarDeleteById() {
        doNothing().when(eventoRepository).deleteById(1);

        eventoService.eliminarEvento(1);

        verify(eventoRepository).deleteById(1);
    }
}