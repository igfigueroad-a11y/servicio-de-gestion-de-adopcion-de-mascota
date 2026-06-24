package cl.duoc.ms_05postulacion.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostulacionDto {
private Integer IdPostulacion;
private Integer IdUsuario;
private Integer IdMascota;
private LocalDateTime FechaPostulacion;
private String MotivoAdopcion;
private Boolean TieneMascotasPrevias;
private String TipoVivienda;
private Boolean TienePatio;
private String Estado;
private LocalDateTime FechaRespuesta;
}
