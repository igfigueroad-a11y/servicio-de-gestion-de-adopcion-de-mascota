package cl.duoc.Ms_02Acceso.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.Ms_02Acceso.client.UsuarioClient;
import cl.duoc.Ms_02Acceso.dto.AccesoDto;
import cl.duoc.Ms_02Acceso.dto.UsuarioDto;
import cl.duoc.Ms_02Acceso.model.Acceso;
import cl.duoc.Ms_02Acceso.repository.AccesoRepository;

@Service

public class AccesoService {
@Autowired
private AccesoRepository acr;
@Autowired
private UsuarioClient usuarioClient;

public List<AccesoDto> listarCuentas() {
    List <Acceso> cuentas = acr.findAll();
    List <AccesoDto> cuentasDto = new ArrayList<>();
    for (Acceso cuenta : cuentas) {
        cuentasDto.add(convertirADto(cuenta));
    }
    return cuentasDto;
}

public AccesoDto crearAcceso(Acceso ac) {
    UsuarioDto usuario = usuarioClient.buscarUsuarioPorId(ac.getIdUsuario());
    if (usuario == null) {
        throw new RuntimeException("El usuario no existe en MS-01");
    }
    if (acr.existsByNombreUsuario(ac.getNombreUsuario())) {
        throw new RuntimeException("El nombre de usuario ya existe");
    }
    if (acr.existsByIdUsuario(ac.getIdUsuario())) {
        throw new RuntimeException("Este usuario ya tiene una cuenta registrada");
    }
    ac.setCuentaActiva(true);
    Acceso guardado = acr.save(ac);
    return convertirADto(guardado);
}

public AccesoDto buscarPorId(Integer id) {
    return convertirADto(acr.findById(id)
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada")));
}

public AccesoDto buscarPorNombreUsuario(String nombreUsuario) {
        return convertirADto(acr.findByNombreUsuario(nombreUsuario)
                  .orElseThrow(() -> new RuntimeException("Cuenta no encontrada")));
    }

public AccesoDto actualizarAccesoDto(Integer Id, Acceso ac){
Acceso a = acr.findById(Id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        a.setNombreUsuario(ac.getNombreUsuario());
        a.setContraseña(ac.getContraseña());
        a.setCuentaActiva(ac.getCuentaActiva());
        acr.save(a);
        return convertirADto(a);
    }

public void eliminarAcceso(Integer id){
    if (!acr.existsById(id)) {
        throw new RuntimeException("Cuenta no encontrada");
    }
    acr.deleteById(id);
}

private AccesoDto convertirADto (Acceso ac){
    AccesoDto dto = new AccesoDto();
    dto.setIdCuenta(ac.getIdCuenta());
    dto.setIdUsuario(ac.getIdUsuario());
    dto.setNombreUsuario(ac.getNombreUsuario());
    dto.setCuentaActiva(ac.getCuentaActiva());
    return dto;
}





















}
