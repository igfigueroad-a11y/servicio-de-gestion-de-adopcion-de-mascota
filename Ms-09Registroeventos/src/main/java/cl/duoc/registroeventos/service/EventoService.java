package cl.duoc.registroeventos.service;

import java.util.List;
import cl.duoc.registroeventos.model.Evento;
import cl.duoc.registroeventos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Evento guardarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento guardarEventoPorId(Integer id) {
        return eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
}

    public void eliminarEvento(Integer id) {
        eventoRepository.deleteById(id);
    }
}
