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
    private Integer idPostulacion;

    @Schema(description = "Identificador del usuario que realiza la postulación.")
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;   

    @Schema(description = "Identificador de la mascota para la cual se realiza la postulación.")
    @Column(name = "id_mascota", nullable = false)
    private Integer idMascota;      

    @Schema(description = "Fecha y hora en que se realiza la postulación.")
    @Column(name = "fecha_postulacion", nullable = false)
    private LocalDateTime fechaPostulacion;

    @Schema(description = "Motivo de la adopción.")
    @Column(name = "motivo_adopcion")
    private String motivoAdopcion;

    @Schema(description = "Indica si el postulante ya tiene mascotas.")
    @Column(name = "tiene_mascotas_previas")
    private Boolean tieneMascotasPrevias;

    @Schema(description = "Tipo de vivienda del postulante.")
    @Column(name = "tipo_vivienda")
    private String tipoVivienda;

    @Schema(description = "Indica si el postulante tiene patio.")
    @Column(name = "tiene_patio")
    private Boolean tienePatio;

    @Schema(description = "Estado de la postulación.")
    @Column(name = "estado", nullable = false)
    private String estado;             

    @Schema(description = "Fecha y hora en que se responde a la postulación.")
    @Column(name = "fecha_respuesta")
    private LocalDateTime fechaRespuesta;
}
