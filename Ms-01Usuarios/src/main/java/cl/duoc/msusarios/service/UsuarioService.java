package cl.duoc.msusarios.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.msusarios.dto.UsuarioDto;
import cl.duoc.msusarios.model.Usuario;
import cl.duoc.msusarios.repository.UsuarioRepository;
@Service

public class UsuarioService {
@Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDto> buscarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> lista = new ArrayList<>();
        for (Usuario u : usuarios) {
            lista.add(convertirADTO(u));
        }
        return lista;
    }

    public UsuarioDto buscarUsuarioPorId(Integer id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return convertirADTO(u);
    }

    public UsuarioDto buscarPorCorreo(String correo) {
        Usuario u = usuarioRepository.findByCorreoElectronico(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return convertirADTO(u);
    }

    public UsuarioDto crearUsuario(UsuarioDto dto) {
        Usuario u = convertirAEntidad(dto);
        u.setFechaRegistro(LocalDateTime.now());
        usuarioRepository.save(u);
        return convertirADTO(u);
    }
    public UsuarioDto actualizarUsuario(Integer id, UsuarioDto dto) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        u.setNombreCompleto(dto.getNombreCompleto());
        u.setCorreoElectronico(dto.getCorreoElectronico());
        u.setTelefono(dto.getTelefono());
        u.setDireccion(dto.getDireccion());
        u.setCiudad(dto.getCiudad());
        u.setFechaNacimiento(dto.getFechaNacimiento());
        u.setSalarioAproximado(dto.getSalarioAproximado());
        usuarioRepository.save(u);
        return convertirADTO(u);
    }

    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioDto convertirADTO(Usuario u) {
        UsuarioDto dto = new UsuarioDto();
        dto.setIdUsuario(u.getIdUsuario());
        dto.setNombreCompleto(u.getNombreCompleto());
        dto.setCorreoElectronico(u.getCorreoElectronico());
        dto.setTelefono(u.getTelefono());
        dto.setDireccion(u.getDireccion());
        dto.setCiudad(u.getCiudad());
        dto.setFechaNacimiento(u.getFechaNacimiento());
        dto.setSalarioAproximado(u.getSalarioAproximado());
        return dto;
    }

    private Usuario convertirAEntidad(UsuarioDto dto) {
        Usuario u = new Usuario();
        u.setNombreCompleto(dto.getNombreCompleto());
        u.setCorreoElectronico(dto.getCorreoElectronico());
        u.setTelefono(dto.getTelefono());
        u.setDireccion(dto.getDireccion());
        u.setCiudad(dto.getCiudad());
        u.setFechaNacimiento(dto.getFechaNacimiento());
        u.setSalarioAproximado(dto.getSalarioAproximado());
        return u;
    }
}