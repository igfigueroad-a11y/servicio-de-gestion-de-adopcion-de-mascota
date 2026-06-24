package cl.duoc.ms_mascotas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_mascotas.dto.SaludMascotaDto;
import cl.duoc.ms_mascotas.model.SaludMascota;
import cl.duoc.ms_mascotas.repository.MascotaRepository;
import cl.duoc.ms_mascotas.repository.SaludMascotaRepository;

@Service
public class SaludMascotaService {

@Autowired
private SaludMascotaRepository sr;

@Autowired
private MascotaRepository mr;

public List<SaludMascotaDto> ListarSaludes() {
       List<SaludMascotaDto> lista = new ArrayList<>();
        for (SaludMascota s : sr.findAll()) {
            lista.add(convertirADto(s));
        }
        return lista;
    }

public SaludMascotaDto BuscarPorId(Integer id) {
    SaludMascota s = sr.findById(id).orElseThrow(() -> new RuntimeException("Ficha de salud no encontrada"));
        return convertirADto(s);
    }

public SaludMascotaDto BuscarPorIdMascota(Integer idMascota) {
    SaludMascota s = sr.findByIdMascota(idMascota).orElseThrow(() -> new RuntimeException("Ficha de salud no encontrada"));
        return convertirADto(s);
    }

public SaludMascotaDto RegistrarSalud(SaludMascota salud) {
        if (!mr.existsById(salud.getIdMascota())) {
            throw new RuntimeException("La mascota con ID " + salud.getIdMascota() + " no existe");
        }
        if (sr.existsByIdMascota(salud.getIdMascota())) {
            throw new RuntimeException("Esta mascota ya tiene ficha de salud registrada");
        }
        sr.save(salud);
        return convertirADto(salud);
    }

public SaludMascotaDto ActualizarSalud(Integer id, SaludMascota salud) {
        SaludMascota s = sr.findById(id).orElseThrow(() -> new RuntimeException("Ficha de salud no encontrada"));
        s.setVacunada(salud.getVacunada());
        s.setEsterilizada(salud.getEsterilizada());
        s.setDesparasitada(salud.getDesparasitada());
        s.setCondicionGeneral(salud.getCondicionGeneral());
        s.setUltimaRevisionVet(salud.getUltimaRevisionVet());
        s.setObservaciones(salud.getObservaciones());
        sr.save(s);
        return convertirADto(s);
    }

public void EliminarSalud(Integer id) {
        if (!sr.existsById(id)) {
            throw new RuntimeException("Ficha de salud no encontrada");
        }
        sr.deleteById(id);
    }

private SaludMascotaDto convertirADto(SaludMascota s) {
        SaludMascotaDto dto = new SaludMascotaDto();
        dto.setIdSalud(s.getIdSalud());
        dto.setIdMascota(s.getIdMascota());
        dto.setVacunada(s.getVacunada());
        dto.setEsterilizada(s.getEsterilizada());
        dto.setDesparasitada(s.getDesparasitada());
        dto.setCondicionGeneral(s.getCondicionGeneral());
        dto.setUltimaRevisionVet(s.getUltimaRevisionVet());
        dto.setObservaciones(s.getObservaciones());
        return dto;
    }

}

