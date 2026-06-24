package cl.duoc.registroeventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.registroeventos.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}
