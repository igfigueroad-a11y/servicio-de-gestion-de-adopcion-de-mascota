package cl.duoc.registroeventos.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class MascotaDTO {
    private Integer idMascota;
    private Integer idTipoMascota;
    private String nombreMascota;
    private String especieMascota;
    private String raza;
    private Integer edad;
    private String descripcion;
    private String disponibilidad;
    private Date fechaIngreso;


}
