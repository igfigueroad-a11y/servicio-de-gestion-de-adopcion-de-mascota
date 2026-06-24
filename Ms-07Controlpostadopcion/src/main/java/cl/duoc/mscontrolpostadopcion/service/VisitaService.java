package cl.duoc.mscontrolpostadopcion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mscontrolpostadopcion.model.Visita;
import cl.duoc.mscontrolpostadopcion.repository.VisitaRepository;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    public List<Visita> listarVisitas() {
        return visitaRepository.findAll();
    }

    public Visita guardarVisita(Visita visita) {
        return visitaRepository.save(visita);
    }

    public Visita buscarVisitaPorId(Integer id) {
        return visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada"));
    }

    public void eliminarVisita(Integer id) {
        if (!visitaRepository.existsById(id)) {
            throw new RuntimeException("Visita no encontrada");
        }
        visitaRepository.deleteById(id);
    }
}