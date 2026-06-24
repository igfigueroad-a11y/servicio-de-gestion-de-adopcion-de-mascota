package cl.duoc.mensajes.service;


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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.mensajes.model.Aviso;
import cl.duoc.mensajes.repository.AvisoRepository;

@ExtendWith(MockitoExtension.class)
public class AvisoServiceTest {

    @Mock
    private AvisoRepository mensajesRepository;

    @InjectMocks
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
    void listarMensajes_debeRetornarLista() {
        when(mensajesRepository.findAll()).thenReturn(Arrays.asList(aviso));

        List<Aviso> resultado = avisoService.listarMensajes();

        assertEquals(1, resultado.size());
        assertEquals("Recordatorio", resultado.get(0).getAsunto());
    }

    @Test
    void guardarAviso_debeRetornarAvisoGuardado() {
        when(mensajesRepository.save(any(Aviso.class))).thenReturn(aviso);

        Aviso resultado = avisoService.guardarAviso(aviso);

        assertEquals(1, resultado.getId());
        assertEquals("EMAIL", resultado.getCanalEnvio());
    }

    @Test
    void buscarAvisoPorId_debeRetornarAvisoSiExiste() {
        when(mensajesRepository.findById(1)).thenReturn(Optional.of(aviso));

        Aviso resultado = avisoService.buscarAvisoPorId(1);

        assertEquals(1, resultado.getId());
        assertEquals("ACTUALIZACION", resultado.getTipoEvento());
    }

    @Test
    void buscarAvisoPorId_debeLanzarExcepcionSiNoExiste() {
        when(mensajesRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> avisoService.buscarAvisoPorId(99));

        assertEquals("Aviso no encontrado con id: 99", ex.getMessage());
    }

    @Test
    void eliminarAviso_debeEjecutarDeleteById() {
        doNothing().when(mensajesRepository).deleteById(1);

        avisoService.eliminarAviso(1);

        verify(mensajesRepository).deleteById(1);
    }
}