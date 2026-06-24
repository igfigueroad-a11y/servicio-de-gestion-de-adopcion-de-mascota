package cl.duoc.estadisticas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.estadisticas.model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {

}
