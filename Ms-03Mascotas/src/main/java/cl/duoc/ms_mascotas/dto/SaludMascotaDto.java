package cl.duoc.ms_mascotas.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data  
@AllArgsConstructor
@NoArgsConstructor

public class SaludMascotaDto {

private Integer IdSalud;
private Integer IdMascota;
private Boolean Vacunada;
private Boolean Esterilizada;
private Boolean Desparasitada;
private String CondicionGeneral;
private String UltimaRevisionVet;
private String Observaciones;

}
