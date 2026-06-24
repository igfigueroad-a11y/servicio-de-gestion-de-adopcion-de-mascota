package cl.duoc.Ms_02Acceso.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import cl.duoc.Ms_02Acceso.client.UsuarioClient;
import cl.duoc.Ms_02Acceso.dto.AccesoDto;
import cl.duoc.Ms_02Acceso.dto.UsuarioDto;
import cl.duoc.Ms_02Acceso.model.Acceso;
import cl.duoc.Ms_02Acceso.repository.AccesoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class accesoServiceTest {

    @Mock
    private AccesoRepository acr;

    @Mock
    private UsuarioClient usuarioClient;

    @InjectMocks
    private AccesoService accesoService;

    private Acceso acceso;
    private UsuarioDto usuarioDto;

    @BeforeEach
    void setUp() {
        acceso = new Acceso();
        acceso.setIdCuenta(1);
        acceso.setIdUsuario(10);
        acceso.setNombreUsuario("jperez");
        acceso.setContraseña("1234");
        acceso.setCuentaActiva(true);

        usuarioDto = new UsuarioDto();
    }

    @Test
    void listarCuentas_debeRetornarListaDto() {
        when(acr.findAll()).thenReturn(Arrays.asList(acceso));

        List<AccesoDto> resultado = accesoService.listarCuentas();

        assertEquals(1, resultado.size());
        assertEquals("jperez", resultado.get(0).getNombreUsuario());
    }

    @Test
    void crearAcceso_debeCrearCuentaSiTodoEstaBien() {
        when(usuarioClient.buscarUsuarioPorId(10)).thenReturn(usuarioDto);
        when(acr.existsByNombreUsuario("jperez")).thenReturn(false);
        when(acr.existsByIdUsuario(10)).thenReturn(false);
        when(acr.save(any(Acceso.class))).thenReturn(acceso);

        AccesoDto resultado = accesoService.crearAcceso(acceso);

        assertEquals("jperez", resultado.getNombreUsuario());
        assertEquals(10, resultado.getIdUsuario());
        assertEquals(true, resultado.getCuentaActiva());
    }

    @Test
    void crearAcceso_debeLanzarExcepcionSiUsuarioNoExiste() {
        when(usuarioClient.buscarUsuarioPorId(10)).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> accesoService.crearAcceso(acceso));

        assertEquals("El usuario no existe en MS-01", ex.getMessage());
    }

    @Test
    void crearAcceso_debeLanzarExcepcionSiNombreUsuarioYaExiste() {
        when(usuarioClient.buscarUsuarioPorId(10)).thenReturn(usuarioDto);
        when(acr.existsByNombreUsuario("jperez")).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> accesoService.crearAcceso(acceso));

        assertEquals("El nombre de usuario ya existe", ex.getMessage());
    }

    @Test
    void crearAcceso_debeLanzarExcepcionSiUsuarioYaTieneCuenta() {
        when(usuarioClient.buscarUsuarioPorId(10)).thenReturn(usuarioDto);
        when(acr.existsByNombreUsuario("jperez")).thenReturn(false);
        when(acr.existsByIdUsuario(10)).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> accesoService.crearAcceso(acceso));

        assertEquals("Este usuario ya tiene una cuenta registrada", ex.getMessage());
    }

    @Test
    void buscarPorId_debeRetornarCuenta() {
        when(acr.findById(1)).thenReturn(Optional.of(acceso));

        AccesoDto resultado = accesoService.buscarPorId(1);

        assertEquals("jperez", resultado.getNombreUsuario());
    }

    @Test
    void buscarPorId_debeLanzarExcepcionSiNoExiste() {
        when(acr.findById(99)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> accesoService.buscarPorId(99));

        assertEquals("Cuenta no encontrada", ex.getMessage());
    }

    @Test
    void buscarPorNombreUsuario_debeRetornarCuenta() {
        when(acr.findByNombreUsuario("jperez")).thenReturn(Optional.of(acceso));

        AccesoDto resultado = accesoService.buscarPorNombreUsuario("jperez");

        assertEquals(10, resultado.getIdUsuario());
    }

    @Test
    void actualizarAccesoDto_debeActualizarCuenta() {
        Acceso actualizado = new Acceso();
        actualizado.setNombreUsuario("juanp");
        actualizado.setContraseña("abcd");
        actualizado.setCuentaActiva(false);

        when(acr.findById(1)).thenReturn(Optional.of(acceso));
        when(acr.save(any(Acceso.class))).thenReturn(acceso);

        AccesoDto resultado = accesoService.actualizarAccesoDto(1, actualizado);

        assertEquals("juanp", resultado.getNombreUsuario());
        assertEquals(false, resultado.getCuentaActiva());
    }

    @Test
    void eliminarAcceso_debeEliminarSiExiste() {
        when(acr.existsById(1)).thenReturn(true);
        doNothing().when(acr).deleteById(1);

        accesoService.eliminarAcceso(1);

        verify(acr).deleteById(1);
    }

    @Test
    void eliminarAcceso_debeLanzarExcepcionSiNoExiste() {
        when(acr.existsById(99)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> accesoService.eliminarAcceso(99));

        assertEquals("Cuenta no encontrada", ex.getMessage());
    }
}