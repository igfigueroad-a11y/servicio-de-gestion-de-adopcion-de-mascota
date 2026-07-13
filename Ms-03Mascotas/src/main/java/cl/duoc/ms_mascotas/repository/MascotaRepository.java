package cl.duoc.ms_mascotas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.ms_mascotas.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    List<Mascota> findByDisponibilidad(String disponibilidad);
    List<Mascota> findByEspecieMascota(String especieMascota);
    List<Mascota> findByIdTipoMascota(Integer idTipoMascota);
}