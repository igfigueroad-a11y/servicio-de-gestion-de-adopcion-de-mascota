package cl.duoc.msprocesoadopcion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.msprocesoadopcion.model.Adopcion;
@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Integer> {
    

        
}
