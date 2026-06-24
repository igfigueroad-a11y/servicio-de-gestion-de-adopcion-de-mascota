package cl.duoc.ms_05postulacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.ms_05postulacion.model.Postulacion;
@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, Integer> {

    List<Postulacion> findByIdUsuario(Integer idUsuario);
    List<Postulacion> findByIdMascota(Integer idMascota);
    List<Postulacion> findByEstado(String estado);
    boolean existsByIdUsuarioAndIdMascota(Integer idUsuario, Integer idMascota);
}
