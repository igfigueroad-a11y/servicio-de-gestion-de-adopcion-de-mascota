package cl.duoc.mensajes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.duoc.mensajes.model.Aviso;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Integer> {

    

}
