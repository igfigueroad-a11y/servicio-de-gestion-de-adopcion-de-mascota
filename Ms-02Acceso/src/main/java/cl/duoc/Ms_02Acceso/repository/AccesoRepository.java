package cl.duoc.Ms_02Acceso.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.Ms_02Acceso.model.Acceso;
@Repository

public interface AccesoRepository extends JpaRepository<Acceso,Integer> {

Optional<Acceso>findByNombreUsuario(String nombreUsuario);

boolean existsByIdUsuario(Integer idUsuario);
boolean existsByNombreUsuario(String nombreUsuario);

}
