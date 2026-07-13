package cl.duoc.Ms_02Acceso.model;

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

@Entity
@Table(name = "acceso")
@Data
@NoArgsConstructor
@AllArgsConstructor



@Schema(description = "Esto representa un acceso en el sistema")
public class Acceso {
    @Schema(description = "Identificador único del acceso")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_cuentas")
    private Integer idCuenta;
    @Schema(description = "Identificador único del usuario")
    @Column(name = "id_usuario", nullable = false, unique = true) 
private Integer idUsuario;

    @Schema(description = "Nombre de usuario")
    @Column(name = "nombre_usuario", nullable = false, unique = true)
private String nombreUsuario; 

    @Schema(description = "Contraseña del usuario")
    @Column(name = "contraseña", nullable = false)
private String contraseña;

   @Schema(description = "Indica si la cuenta está activa")
   @Column(name = "cuenta_activa", nullable = false)
private Boolean cuentaActiva;


}
