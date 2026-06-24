package cl.duoc.ms_04tipomascota.service;


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

import cl.duoc.ms_04tipomascota.dto.TipoMascotaDto;
import cl.duoc.ms_04tipomascota.model.TipoMascota;
import cl.duoc.ms_04tipomascota.repository.TipoMascotaRepository;

@ExtendWith(MockitoExtension.class)
public class TipoMascotaServiceTest {

    @Mock
    private TipoMascotaRepository tpmr;

    @InjectMocks
    private TipoMascotaService tpms;

    private TipoMascota tipoMascota;

    @BeforeEach
    void setUp() {
        tipoMascota = new TipoMascota();
        tipoMascota.setIdTipoMascota(1);
        tipoMascota.setNombreTipo("Perro");
        tipoMascota.setDescripcion("Mascota canina");
    }

    @Test
    void listarTipoMascota_retornaLista() {
        when(tpmr.findAll()).thenReturn(Arrays.asList(tipoMascota));

        List<TipoMascotaDto> resultado = tpms.ListarTipoMascota();

        assertEquals(1, resultado.size());
        assertEquals("Perro", resultado.get(0).getNombreTipo());
    }

    @Test
    void listarTipoMascota_retornaListaVacia() {
        when(tpmr.findAll()).thenReturn(Collections.emptyList());

        List<TipoMascotaDto> resultado = tpms.ListarTipoMascota();

        assertEquals(0, resultado.size());
    }

    @Test
    void guardarTipo_retornaDto() {
        when(tpmr.existsByNombreTipo("Perro")).thenReturn(false);
        when(tpmr.save(tipoMascota)).thenReturn(tipoMascota);

        TipoMascotaDto resultado = tpms.GuardarTipo(tipoMascota);

        assertEquals("Perro", resultado.getNombreTipo());
        assertEquals("Mascota canina", resultado.getDescripcion());
        verify(tpmr).save(tipoMascota);
    }

    @Test
    void guardarTipo_lanzaError_siYaExiste() {
        when(tpmr.existsByNombreTipo("Perro")).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> tpms.GuardarTipo(tipoMascota));

        assertEquals("Ya existe un tipo con ese nombre", ex.getMessage());
    }

    @Test
    void buscarPorId_retornaDto() {
        when(tpmr.findById(1)).thenReturn(Optional.of(tipoMascota));

        TipoMascotaDto resultado = tpms.BuscarPorId(1);

        assertEquals("Perro", resultado.getNombreTipo());
    }

    @Test
    void buscarPorId_lanzaError_siNoExiste() {
        when(tpmr.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> tpms.BuscarPorId(99));

        assertEquals("Tipo de mascota no encontrado", ex.getMessage());
    }

    @Test
    void actualizarTipo_retornaDto() {
        TipoMascota actualizado = new TipoMascota();
        actualizado.setNombreTipo("Gato");
        actualizado.setDescripcion("Mascota felina");

        when(tpmr.findById(1)).thenReturn(Optional.of(tipoMascota));
        when(tpmr.save(tipoMascota)).thenReturn(tipoMascota);

        TipoMascotaDto resultado = tpms.ActualizarTipo(1, actualizado);

        assertEquals("Gato", resultado.getNombreTipo());
        assertEquals("Mascota felina", resultado.getDescripcion());
        verify(tpmr).save(tipoMascota);
    }

    @Test
    void actualizarTipo_lanzaError_siNoExiste() {
        TipoMascota actualizado = new TipoMascota();
        actualizado.setNombreTipo("Gato");
        actualizado.setDescripcion("Mascota felina");

        when(tpmr.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> tpms.ActualizarTipo(99, actualizado));

        assertEquals("Tipo de mascota no encontrado", ex.getMessage());
    }

    @Test
    void eliminarTipo_eliminaCorrectamente() {
        when(tpmr.existsById(1)).thenReturn(true);
        doNothing().when(tpmr).deleteById(1);

        tpms.EliminarTipo(1);

        verify(tpmr).deleteById(1);
    }

    @Test
    void eliminarTipo_lanzaError_siNoExiste() {
        when(tpmr.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> tpms.EliminarTipo(99));

        assertEquals("Tipo de mascota no encontrado", ex.getMessage());
    }
}