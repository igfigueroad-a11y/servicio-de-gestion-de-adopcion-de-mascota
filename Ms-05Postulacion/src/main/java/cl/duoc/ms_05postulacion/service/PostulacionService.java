package cl.duoc.ms_05postulacion.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_05postulacion.client.MascotasClient;
import cl.duoc.ms_05postulacion.client.UsuarioClient;
import cl.duoc.ms_05postulacion.dto.PostulacionDto;
import cl.duoc.ms_05postulacion.model.Postulacion;
import cl.duoc.ms_05postulacion.repository.PostulacionRepository;

@Service
public class PostulacionService {

@Autowired
private PostulacionRepository pr;

@Autowired
private UsuarioClient usuarioClient;

@Autowired
private MascotasClient mascotaClient;

public List<PostulacionDto> ListarPostulaciones() {
    List<PostulacionDto> lista = new ArrayList<>();
    for (Postulacion p : pr.findAll()) {
        lista.add(convertirADto(p));
        }
        return lista;
    }

public PostulacionDto BuscarPorId(Integer id) {
    Postulacion p = pr.findById(id).orElseThrow(() -> new RuntimeException("Postulacion no encontrada"));
    return convertirADto(p);
    }

public List<PostulacionDto> BuscarPorUsuario(Integer idUsuario) {
    List<PostulacionDto> lista = new ArrayList<>();
    for (Postulacion p : pr.findByIdUsuario(idUsuario)) {
        lista.add(convertirADto(p));
        }
        return lista;
    }

public List<PostulacionDto> BuscarPorEstado(String estado) {
    List<PostulacionDto> lista = new ArrayList<>();
    for (Postulacion p : pr.findByEstado(estado)) {
        lista.add(convertirADto(p));
        }
        return lista;
    }
public PostulacionDto GuardarPostulacion(Postulacion postulacion) {
        try {
            usuarioClient.buscarUsuarioPorId(postulacion.getIdUsuario());
        } catch (Exception e) {
            throw new RuntimeException("El usuario con ID "
                    + postulacion.getIdUsuario() + " no existe en MS-01");
        }

        try {
            mascotaClient.buscarMascotaPorId(postulacion.getIdMascota());
        } catch (Exception e) {
            throw new RuntimeException("La mascota con ID "
                    + postulacion.getIdMascota() + " no existe en MS-03");
        }

        if (pr.existsByIdUsuarioAndIdMascota(
                postulacion.getIdUsuario(), postulacion.getIdMascota())) {
            throw new RuntimeException("El usuario ya postuló a esta mascota");
        }

        postulacion.setFechaPostulacion(LocalDateTime.now());
        postulacion.setEstado("pendiente");
        pr.save(postulacion);
        return convertirADto(postulacion);
    }
public PostulacionDto CambiarEstado(Integer id, String estado) {
        Postulacion p = pr.findById(id).orElseThrow(() -> new RuntimeException("Postulacion no encontrada"));
        p.setEstado(estado);
        p.setFechaRespuesta(LocalDateTime.now());
        pr.save(p);
        return convertirADto(p);
    }

public void EliminarPostulacion(Integer id) {
        if (!pr.existsById(id)) {
            throw new RuntimeException("Postulacion no encontrada");
        }
        pr.deleteById(id);
    }

private PostulacionDto convertirADto(Postulacion p) {
        PostulacionDto dto = new PostulacionDto();
        dto.setIdPostulacion(p.getIdPostulacion());
        dto.setIdUsuario(p.getIdUsuario());
        dto.setIdMascota(p.getIdMascota());
        dto.setFechaPostulacion(p.getFechaPostulacion());
        dto.setMotivoAdopcion(p.getMotivoAdopcion());
        dto.setTieneMascotasPrevias(p.getTieneMascotasPrevias());
        dto.setTipoVivienda(p.getTipoVivienda());
        dto.setTienePatio(p.getTienePatio());
        dto.setEstado(p.getEstado());
        dto.setFechaRespuesta(p.getFechaRespuesta());
        return dto;
    }
}
