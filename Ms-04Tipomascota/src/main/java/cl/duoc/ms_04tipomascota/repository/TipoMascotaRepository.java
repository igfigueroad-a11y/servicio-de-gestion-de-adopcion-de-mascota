package cl.duoc.ms_04tipomascota.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.duoc.ms_04tipomascota.model.TipoMascota;
@Repository

public interface TipoMascotaRepository extends JpaRepository<TipoMascota, Integer> {
boolean existsByNombreTipo(String nombreTipo);
Optional<TipoMascota> findByNombreTipo(String nombreTipo);
}
