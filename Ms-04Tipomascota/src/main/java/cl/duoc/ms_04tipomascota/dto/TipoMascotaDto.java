package cl.duoc.ms_04tipomascota.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TipoMascotaDto {
private Integer IdTipoMascota;
private String NombreTipo;
private String Descripcion;
}
