package cl.duoc.msusarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.msusarios.dto.UsuarioDto;
import cl.duoc.msusarios.model.Usuario;
import cl.duoc.msusarios.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuariosServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioejem;
    private UsuarioDto usuarioDtoejem;

    @BeforeEach
    void setUp() {
        usuarioejem = new Usuario();
        usuarioejem.setIdUsuario(1);
        usuarioejem.setNombreCompleto("ignacio figueroa");
        usuarioejem.setCorreoElectronico("ignacio@correo.com");

        usuarioDtoejem = new UsuarioDto();
        usuarioDtoejem.setIdUsuario(1);
        usuarioDtoejem.setNombreCompleto("ignacio figueroa");
        usuarioDtoejem.setCorreoElectronico("ignacio@correo.com");
    }

    @Test
    void listar() {
        List<Usuario> listafalsa = new ArrayList<>();
        listafalsa.add(usuarioejem);
        when(usuarioRepository.findAll()).thenReturn(listafalsa);

        List<UsuarioDto> listaUsuarios = usuarioService.buscarUsuarios();

        assertEquals(1, listaUsuarios.size());
    }

    @Test
    void buscarPorId_encontrado() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioejem));

        UsuarioDto resultado = usuarioService.buscarUsuarioPorId(1);

        assertEquals(1, resultado.getIdUsuario());
        assertEquals("ignacio figueroa", resultado.getNombreCompleto());
    }

    @Test
    void buscarPorId_noEncontrado() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            usuarioService.buscarUsuarioPorId(99);
        });
    }

    @Test
    void buscarPorCorreo_encontrado() {
        when(usuarioRepository.findByCorreoElectronico("ignacio@correo.com"))
                .thenReturn(Optional.of(usuarioejem));

        UsuarioDto resultado = usuarioService.buscarPorCorreo("ignacio@correo.com");

        assertEquals("ignacio@correo.com", resultado.getCorreoElectronico());
    }

    @Test
    void buscarPorCorreo_noEncontrado() {
        when(usuarioRepository.findByCorreoElectronico("noexiste@correo.com"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            usuarioService.buscarPorCorreo("noexiste@correo.com");
        });
    }

    @Test
    void crearUsuario() {
        when(usuarioRepository.save(org.mockito.ArgumentMatchers.any(Usuario.class)))
                .thenReturn(usuarioejem);

        UsuarioDto resultado = usuarioService.crearUsuario(usuarioDtoejem);

        assertEquals("ignacio figueroa", resultado.getNombreCompleto());
        assertEquals("ignacio@correo.com", resultado.getCorreoElectronico());
    }

    @Test
    void actualizarUsuario_encontrado() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioejem));
        when(usuarioRepository.save(org.mockito.ArgumentMatchers.any(Usuario.class)))
                .thenReturn(usuarioejem);

        UsuarioDto resultado = usuarioService.actualizarUsuario(1, usuarioDtoejem);

        assertEquals("ignacio figueroa", resultado.getNombreCompleto());
    }

    @Test
    void actualizarUsuario_noEncontrado() {
        when(usuarioRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            usuarioService.actualizarUsuario(99, usuarioDtoejem);
        });
    }

    @Test
    void eliminarUsuario_encontrado() {
        when(usuarioRepository.existsById(1)).thenReturn(true);

        usuarioService.eliminarUsuario(1);

        verify(usuarioRepository, times(1)).deleteById(1);
    }

    @Test
    void eliminarUsuario_noEncontrado() {
        when(usuarioRepository.existsById(99)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> {
            usuarioService.eliminarUsuario(99);
        });
    }
}