package cl.duoc.ms_mascotas.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.ms_mascotas.client.TipoMascotaClient;
import cl.duoc.ms_mascotas.dto.MascotaDto;
import cl.duoc.ms_mascotas.dto.TipoMascotaDto;
import cl.duoc.ms_mascotas.model.Mascota;
import cl.duoc.ms_mascotas.repository.MascotaRepository;

@ExtendWith(MockitoExtension.class)
public class MascotaServiceTest {

    @Mock
    private MascotaRepository mr;

    @Mock
    private TipoMascotaClient Tpmc;

    @InjectMocks
    private MascotaService ms;

    private Mascota mascota;

    @BeforeEach
    void setUp() {
        mascota = new Mascota();
        mascota.setIdMascota(1);
        mascota.setIdTipoMascota(10);
        mascota.setNombreMascota("Firulais");
        mascota.setEspecieMascota("Perro");
        mascota.setRaza("Mestizo");
        mascota.setEdad(3);
        mascota.setDescripcion("Mascota amigable");
        mascota.setDisponibilidad("disponible");
        mascota.setFechaIngreso(LocalDate.now());
    }

    @Test
    void listarMascotas_retornaLista() {
        when(mr.findAll()).thenReturn(Arrays.asList(mascota));

        var resultado = ms.ListarMascotas();

        assertEquals(1, resultado.size());
        assertEquals("Firulais", resultado.get(0).getNombreMascota());
    }

@Test
void guardarMascota_retornaDto() {
    TipoMascotaDto tipo = new TipoMascotaDto();
    tipo.setIdTipoMascota(10);

    when(Tpmc.buscarTipoPorId(10)).thenReturn(tipo);
    when(mr.save(mascota)).thenReturn(mascota);

    MascotaDto resul = ms.GuardarMascota(mascota);

    assertNotNull(resul);
    assertEquals("Firulais", resul.getNombreMascota());
}

    @Test
    void guardarMascota_lanzaError_siTipoNoExiste() {
        when(Tpmc.buscarTipoPorId(10)).thenThrow(new RuntimeException());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ms.GuardarMascota(mascota));

        assertEquals("El tipo de mascota con ID 10 no existe en el sistema", ex.getMessage());
    }

    @Test
    void buscarPorId_retornaMascota() {
        when(mr.findById(1)).thenReturn(Optional.of(mascota));

        MascotaDto resul = ms.BuscarPorId(1);

        assertEquals("Firulais", resul.getNombreMascota());
    }

    @Test
    void buscarPorId_lanzaError_siNoExiste() {
        when(mr.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ms.BuscarPorId(99));

        assertEquals("Mascota no encontrada", ex.getMessage());
    }

    @Test
    void filtrarPorEspecie_retornaLista() {
        when(mr.findByEspecieMascota("Perro")).thenReturn(Arrays.asList(mascota));

        var resul= ms.FiltrarPorEspecie("Perro");

        assertEquals(1, resul.size());
        assertEquals("Perro", resul.get(0).getEspecieMascota());
    }

    @Test
    void filtrarPorEspecie_retornaListaVacia() {
        when(mr.findByEspecieMascota("Dragon")).thenReturn(Collections.emptyList());

        var resul= ms.FiltrarPorEspecie("Dragon");

        assertEquals(0, resul.size());
    }

    @Test
    void eliminarMascota_eliminaCorrectamente() {
        when(mr.existsById(1)).thenReturn(true);
        doNothing().when(mr).deleteById(1);

        ms.EliminarMascota(1);

        verify(mr).deleteById(1);
    }

}
