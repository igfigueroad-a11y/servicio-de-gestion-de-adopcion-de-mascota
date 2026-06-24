package cl.duoc.ms_04tipomascota.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_04tipomascota.dto.TipoMascotaDto;
import cl.duoc.ms_04tipomascota.model.TipoMascota;
import cl.duoc.ms_04tipomascota.repository.TipoMascotaRepository;

@Service
public class TipoMascotaService {
@Autowired
  private TipoMascotaRepository tpmr;
  public List<TipoMascotaDto> ListarTipoMascota(){
    List<TipoMascotaDto> lista = new ArrayList<>();
    for (TipoMascota t : tpmr.findAll()) {
            lista.add(convertirADto(t));
        }
        return lista;
    }
   
public TipoMascotaDto GuardarTipo(TipoMascota tipo) {
        if (tpmr.existsByNombreTipo(tipo.getNombreTipo())) {
            throw new RuntimeException("Ya existe un tipo con ese nombre");
        }
        tpmr.save(tipo);
        return convertirADto(tipo);

    }
public TipoMascotaDto BuscarPorId(Integer id) {
        TipoMascota t = tpmr.findById(id).orElseThrow(() -> new RuntimeException("Tipo de mascota no encontrado"));
        return convertirADto(t);
    }

public TipoMascotaDto ActualizarTipo(Integer id, TipoMascota tipo) {
        TipoMascota t = tpmr.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de mascota no encontrado"));
        t.setNombreTipo(tipo.getNombreTipo());
        t.setDescripcion(tipo.getDescripcion());
        tpmr.save(t);
        return convertirADto(t);
    }
public void EliminarTipo(Integer id) {
        if (!tpmr.existsById(id)) {
            throw new RuntimeException("Tipo de mascota no encontrado");
        }
        tpmr.deleteById(id);
    }
 private TipoMascotaDto convertirADto(TipoMascota t) {
        TipoMascotaDto dto = new TipoMascotaDto();
        dto.setIdTipoMascota(t.getIdTipoMascota());
        dto.setNombreTipo(t.getNombreTipo());
        dto.setDescripcion(t.getDescripcion());
        return dto;
    }

}
