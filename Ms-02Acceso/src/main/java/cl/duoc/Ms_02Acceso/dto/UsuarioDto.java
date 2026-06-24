package cl.duoc.Ms_02Acceso.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    private LocalDate fechaNacimiento;
    private BigDecimal salarioAproximado;
}
