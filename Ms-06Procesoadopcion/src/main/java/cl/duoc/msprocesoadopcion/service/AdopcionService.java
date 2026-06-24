package cl.duoc.msprocesoadopcion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.msprocesoadopcion.model.Adopcion;
import cl.duoc.msprocesoadopcion.repository.AdopcionRepository;

@Service
public class AdopcionService {

    @Autowired
    private AdopcionRepository adopcionRepository;

    public List<Adopcion> listarAdopciones() {
        return adopcionRepository.findAll();
    }

    public Adopcion guardarAdopcion(Adopcion adopcion) {
        return adopcionRepository.save(adopcion);
    }

    public Adopcion buscarAdopcionPorId(Integer id) {
        return adopcionRepository.findById(id).orElseThrow(() -> new RuntimeException("Adopción no encontrada"));
    }

   public void eliminarAdopcion(Integer id) {
    if (!adopcionRepository.existsById(id)) {
        throw new RuntimeException("Adopción no encontrada");
    }
    adopcionRepository.deleteById(id);
}

}
