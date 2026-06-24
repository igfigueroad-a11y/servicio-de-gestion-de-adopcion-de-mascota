package cl.duoc.registroeventos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.registroeventos.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
