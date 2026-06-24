package cl.duoc.ms_mascotas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_mascotas.client.TipoMascotaClient;
import cl.duoc.ms_mascotas.dto.MascotaDto;
import cl.duoc.ms_mascotas.model.Mascota;
import cl.duoc.ms_mascotas.repository.MascotaRepository;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mr;

    @Autowired
    private TipoMascotaClient Tpmc;

    public List<MascotaDto> ListarMascotas() {
        List<MascotaDto> lista = new ArrayList<>();
        for (Mascota m : mr.findAll()) {
            lista.add(convertirADto(m));
        }
        return lista;
    }                                            

    public MascotaDto GuardarMascota(Mascota mascota) {
        try {
            Tpmc.buscarTipoPorId(mascota.getIdTipoMascota());
        } catch (Exception e) {
            throw new RuntimeException("El tipo de mascota con ID "
                    + mascota.getIdTipoMascota() + " no existe en el sistema");
        }
        mascota.setDisponibilidad("disponible");
        mascota.setFechaIngreso(LocalDate.now());   
        mr.save(mascota);
        return convertirADto(mascota);
    }

    public MascotaDto BuscarPorId(Integer id) {    
        Mascota m = mr.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        return convertirADto(m);
    }

    public List<MascotaDto> FiltrarPorEspecie(String especie) {
        List<MascotaDto> lista = new ArrayList<>();
        for (Mascota m : mr.findByEspecieMascota(especie)) {
            lista.add(convertirADto(m));
        }
        return lista;
    }

    public void EliminarMascota(Integer id) {
        if (!mr.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada");
        }
        mr.deleteById(id);
    }

    private MascotaDto convertirADto(Mascota m) {
        MascotaDto dto = new MascotaDto();
        dto.setIdMascota(m.getIdMascota());
        dto.setIdTipoMascota(m.getIdTipoMascota());
        dto.setNombreMascota(m.getNombreMascota());  
        dto.setEspecieMascota(m.getEspecieMascota());
        dto.setRaza(m.getRaza());
        dto.setEdad(m.getEdad());    
        dto.setDescripcion(m.getDescripcion());
        dto.setDisponibilidad(m.getDisponibilidad());
        dto.setFechaIngreso(m.getFechaIngreso());
        return dto;
    }

}