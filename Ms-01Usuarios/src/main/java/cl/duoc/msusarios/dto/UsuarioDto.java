package cl.duoc.msusarios.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDto {

  private Integer idUsuario;
@NotBlank(message = "El nombre completo es obligatorio")
    private String nombreCompleto;
    
@NotBlank(message = "El correo electrónico es obligatorio")
    private String correoElectronico;
@NotBlank(message = "El teléfono es obligatorio")
    private String telefono;
@NotBlank(message = "La dirección es obligatoria")
    private String direccion;

@NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

@NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
@NotNull(message = "El salario aproximado es obligatorio")
    private BigDecimal salarioAproximado;


}


