package cl.duoc.registroeventos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import cl.duoc.registroeventos.model.Estado;
import cl.duoc.registroeventos.repository.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listarEstados() {
        return estadoRepository.findAll();
    }

    public Estado guardarEstado(Estado estado) {
        return estadoRepository.save(estado);
    }   

    public Estado guardarEstadoPorId(Integer id) {
        return estadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Estado no encontrado con id: " + id));
    }

    public void eliminarEstado(Integer id) {
        estadoRepository.deleteById(id);
    }

}
