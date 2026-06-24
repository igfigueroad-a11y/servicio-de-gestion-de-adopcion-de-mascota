package cl.duoc.ms_05postulacion.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 @Data
 @AllArgsConstructor
 @NoArgsConstructor

public class MascotaDto {
private Integer idMascota;
private Integer idTipoMascota;
private String nombreMascota;
private String especieMascota;
private String raza;
private Integer edad;
private String descripcion;
private String disponibilidad;
private LocalDate fechaIngreso;
}
