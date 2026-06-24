package cl.duoc.msusarios.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "usuarios")

@Schema(description = "Esto representa a un usuario en el sistema")
public class Usuario {
@Schema(description = "Identificador unico del usuario")
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
@Schema(description = "Nombre completo del usuario")
    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;
@Schema(description = "Correo electronico del usuario")
    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @Schema(description = "Rol unico tributario del usuario")
    @Column(name = "rut", nullable = false, unique = true)
    private String Rut;
    
    @Schema(description = "Telefono del usuario")
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Schema(description = "Direccion del usuario")
    @Column(name = "direccion", nullable = false)
    private String direccion;
    
    @Schema(description = "Ciudad del usuario")
    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Schema(description = "Fecha de nacimiento del usuario")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @Schema(description = "Fecha de registro del usuario")
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
        
    @Schema(description = "Salario aproximado del usuario")
    @Column(name = "salario_aproximado", nullable = false)
    private BigDecimal salarioAproximado;




}
