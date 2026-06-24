package cl.duoc.ms_mascotas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.ms_mascotas.model.SaludMascota;
@Repository
public interface SaludMascotaRepository extends JpaRepository<SaludMascota,Integer>{
Optional<SaludMascota> findByIdMascota(Integer idMascota);
boolean existsByIdMascota(Integer idMascota);
}
