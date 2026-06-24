package cl.duoc.estadisticas.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Integer idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;
    private String direccion;
    private String ciudad;
    private Date fechaNacimiento;
    private Integer salarioAproximado;

}
