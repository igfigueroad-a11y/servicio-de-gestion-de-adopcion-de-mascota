package cl.duoc.ms_mascotas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TipoMascotaDto {
private Integer idTipoMascota;
private String nombreTipo;
private String descripcion;
}
