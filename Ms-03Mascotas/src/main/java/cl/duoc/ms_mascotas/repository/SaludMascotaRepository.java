package cl.duoc.ms_mascotas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.ms_mascotas.model.SaludMascota;

@Repository
public interface SaludMascotaRepository extends JpaRepository<SaludMascota, Integer> {
    Optional<SaludMascota> findByMascota_IdMascota(Integer idMascota);
    boolean existsByMascota_IdMascota(Integer idMascota);
}