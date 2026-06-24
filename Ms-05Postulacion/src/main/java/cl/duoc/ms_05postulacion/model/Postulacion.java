package cl.duoc.ms_05postulacion.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "postulaciones")

@Schema(description = "Entidad que representa una postulación para la adopción de una mascota.")
public class Postulacion {
    @Schema(description = "Identificador único de la postulación.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdPostulacion;

    @Schema(description = "Identificador del usuario que realiza la postulación.")
    @Column(name = "id_usuario", nullable = false)
    private Integer IdUsuario;   

    @Schema(description = "Identificador de la mascota para la cual se realiza la postulación.")
    @Column(name = "id_mascota", nullable = false)
    private Integer IdMascota;      

    @Schema(description = "Fecha y hora en que se realiza la postulación.")
    @Column(name = "fecha_postulacion", nullable = false)
    private LocalDateTime FechaPostulacion;

    @Schema(description = "Motivo de la adopción.")
    @Column(name = "motivo_adopcion")
    private String MotivoAdopcion;

    @Schema(description = "Indica si el postulante ya tiene mascotas.")
    @Column(name = "tiene_mascotas_previas")
    private Boolean TieneMascotasPrevias;

    @Schema(description = "Tipo de vivienda del postulante.")
    @Column(name = "tipo_vivienda")
    private String TipoVivienda;

    @Schema(description = "Indica si el postulante tiene patio.")
    @Column(name = "tiene_patio")
    private Boolean TienePatio;

    @Schema(description = "Estado de la postulación.")
    @Column(name = "estado", nullable = false)
    private String Estado;             

    @Schema(description = "Fecha y hora en que se responde a la postulación.")
    @Column(name = "fecha_respuesta")
    private LocalDateTime FechaRespuesta;
}
