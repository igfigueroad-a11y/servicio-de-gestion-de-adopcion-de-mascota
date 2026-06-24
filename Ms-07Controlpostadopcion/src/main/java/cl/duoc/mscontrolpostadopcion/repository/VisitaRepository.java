package cl.duoc.mscontrolpostadopcion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.mscontrolpostadopcion.model.Visita;



@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer> {


}
