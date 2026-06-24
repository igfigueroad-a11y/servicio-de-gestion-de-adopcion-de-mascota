package cl.duoc.ms_05postulacion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDto {
private Integer idUsuario;
private String nombreCompleto;
private String correoElectronico;
private String telefono;
private String direccion;
private String ciudad;
}
