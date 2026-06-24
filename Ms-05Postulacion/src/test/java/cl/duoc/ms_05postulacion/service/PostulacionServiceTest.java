package cl.duoc.ms_05postulacion.service;

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

import cl.duoc.ms_05postulacion.client.MascotasClient;
import cl.duoc.ms_05postulacion.client.UsuarioClient;
import cl.duoc.ms_05postulacion.dto.PostulacionDto;
import cl.duoc.ms_05postulacion.model.Postulacion;
import cl.duoc.ms_05postulacion.repository.PostulacionRepository;

@ExtendWith(MockitoExtension.class)
public class PostulacionServiceTest {

    @Mock
    private PostulacionRepository pr;

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private MascotasClient mascotaClient;

    @InjectMocks
    private PostulacionService ps;

    private Postulacion postulacion;

    @BeforeEach
    void setUp() {
        postulacion = new Postulacion();
        postulacion.setIdPostulacion(1);
        postulacion.setIdUsuario(10);
        postulacion.setIdMascota(5);
        postulacion.setEstado("pendiente");
        postulacion.setMotivoAdopcion("Me gustan los perros");
        postulacion.setTieneMascotasPrevias(false);
        postulacion.setTipoVivienda("casa");
        postulacion.setTienePatio(true);
    }

    @Test
    void listarPostulaciones_retornaLista() {
        when(pr.findAll()).thenReturn(Arrays.asList(postulacion));

        List<PostulacionDto> resultado = ps.ListarPostulaciones();

        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getIdUsuario());
    }

    @Test
    void listarPostulaciones_retornaListaVacia() {
        when(pr.findAll()).thenReturn(Collections.emptyList());

        List<PostulacionDto> resultado = ps.ListarPostulaciones();

        assertEquals(0, resultado.size());
    }

    @Test
    void buscarPorId_retornaDto() {
        when(pr.findById(1)).thenReturn(Optional.of(postulacion));

        PostulacionDto resultado = ps.BuscarPorId(1);

        assertEquals(10, resultado.getIdUsuario());
        assertEquals(5, resultado.getIdMascota());
    }

    @Test
    void buscarPorId_lanzaError_siNoExiste() {
        when(pr.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ps.BuscarPorId(99));

        assertEquals("Postulacion no encontrada", ex.getMessage());
    }

    @Test
    void buscarPorUsuario_retornaLista() {
        when(pr.findByIdUsuario(10)).thenReturn(Arrays.asList(postulacion));

        List<PostulacionDto> resultado = ps.BuscarPorUsuario(10);

        assertEquals(1, resultado.size());
        assertEquals(10, resultado.get(0).getIdUsuario());
    }

    @Test
    void buscarPorEstado_retornaLista() {
        when(pr.findByEstado("pendiente")).thenReturn(Arrays.asList(postulacion));

        List<PostulacionDto> resultado = ps.BuscarPorEstado("pendiente");

        assertEquals(1, resultado.size());
        assertEquals("pendiente", resultado.get(0).getEstado());
    }

    @Test
    void guardarPostulacion_retornaDto() {
        when(usuarioClient.buscarUsuarioPorId(10)).thenReturn(null);
        when(mascotaClient.buscarMascotaPorId(5)).thenReturn(null);
        when(pr.existsByIdUsuarioAndIdMascota(10, 5)).thenReturn(false);
        when(pr.save(postulacion)).thenReturn(postulacion);

        PostulacionDto resultado = ps.GuardarPostulacion(postulacion);

        assertEquals("pendiente", resultado.getEstado());
        assertEquals(10, resultado.getIdUsuario());
        verify(pr).save(postulacion);
    }

    @Test
    void guardarPostulacion_lanzaError_siUsuarioNoExiste() {
        when(usuarioClient.buscarUsuarioPorId(10)).thenThrow(new RuntimeException("no existe"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ps.GuardarPostulacion(postulacion));

        assertEquals("El usuario con ID 10 no existe en MS-01", ex.getMessage());
    }

    @Test
    void guardarPostulacion_lanzaError_siMascotaNoExiste() {
        when(usuarioClient.buscarUsuarioPorId(10)).thenReturn(null);
        when(mascotaClient.buscarMascotaPorId(5)).thenThrow(new RuntimeException("no existe"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ps.GuardarPostulacion(postulacion));

        assertEquals("La mascota con ID 5 no existe en MS-03", ex.getMessage());
    }

    @Test
    void guardarPostulacion_lanzaError_siYaPostulo() {
        when(usuarioClient.buscarUsuarioPorId(10)).thenReturn(null);
        when(mascotaClient.buscarMascotaPorId(5)).thenReturn(null);
        when(pr.existsByIdUsuarioAndIdMascota(10, 5)).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ps.GuardarPostulacion(postulacion));

        assertEquals("El usuario ya postuló a esta mascota", ex.getMessage());
    }

    @Test
    void cambiarEstado_retornaDto() {
        when(pr.findById(1)).thenReturn(Optional.of(postulacion));
        when(pr.save(postulacion)).thenReturn(postulacion);

        PostulacionDto resultado = ps.CambiarEstado(1, "aprobado");

        assertEquals("aprobado", resultado.getEstado());
        verify(pr).save(postulacion);
    }

    @Test
    void cambiarEstado_lanzaError_siNoExiste() {
        when(pr.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ps.CambiarEstado(99, "aprobado"));

        assertEquals("Postulacion no encontrada", ex.getMessage());
    }

    @Test
    void eliminarPostulacion_eliminaCorrectamente() {
        when(pr.existsById(1)).thenReturn(true);
        doNothing().when(pr).deleteById(1);

        ps.EliminarPostulacion(1);

        verify(pr).deleteById(1);
    }

    @Test
    void eliminarPostulacion_lanzaError_siNoExiste() {
        when(pr.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> ps.EliminarPostulacion(99));

        assertEquals("Postulacion no encontrada", ex.getMessage());
    }
}