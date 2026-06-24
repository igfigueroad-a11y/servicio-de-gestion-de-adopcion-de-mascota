package cl.duoc.mensajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mensajes.model.Aviso;
import cl.duoc.mensajes.repository.AvisoRepository;

@Service
public class AvisoService {
    @Autowired
    private AvisoRepository mensajesRepository;

    public List<Aviso> listarMensajes() {
        return mensajesRepository.findAll();
    }

    public Aviso guardarAviso(Aviso aviso) {
        return mensajesRepository.save(aviso);
    }

    public Aviso buscarAvisoPorId(Integer id) {
        return mensajesRepository.findById(id).orElseThrow(() -> new RuntimeException("Aviso no encontrado con id: " + id));
    }

    public void eliminarAviso(Integer id) {
        mensajesRepository.deleteById(id);
    }


}
