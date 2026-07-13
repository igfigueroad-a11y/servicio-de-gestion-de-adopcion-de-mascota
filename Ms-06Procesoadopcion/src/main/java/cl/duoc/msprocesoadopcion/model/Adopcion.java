package cl.duoc.msprocesoadopcion.model;

import java.util.Date;

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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "adopcion")

@Schema(description = "Entidad que representa una adopción de mascota")
public class Adopcion {


    @Schema(description = "Identificador único de la adopción")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_adopcion;
    
    @Schema(description = "Identificador de la postulación asociada a la adopción")
    @Column(name = "id_postulacion", nullable = false)
    private Integer id_postulacion;
    
    @Schema(description = "Identificador del usuario asociado a la adopción")
    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;
    
    @Schema(description = "Identificador de la mascota asociada a la adopción")
    @Column(name = "id_mascota", nullable = false)
    private Integer id_mascota;

    @Schema(description = "Fecha de la adopción")
    @Column(name = "fecha_adopcion", nullable = false)
    private Date fecha_adopcion;

    @Schema(description = "Estado de la adopción")
    @Column(name = "estado_adopcion", nullable = false)
    private String estado_adopcion; 

    @Schema(description = "Notas adicionales sobre la adopción")
    @Column(name = "notas_adicionales", nullable = false)
    private String notas_adicionales;

    

    

    
}
