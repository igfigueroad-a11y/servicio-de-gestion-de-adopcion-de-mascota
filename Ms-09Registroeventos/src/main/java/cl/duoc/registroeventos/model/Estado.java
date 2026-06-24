package cl.duoc.registroeventos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estados")

@Schema(description = "Entidad que representa el estado de una mascota en un momento dado.")
public class Estado {

    @Schema(description = "Identificador único del estado.", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "Identificador de la mascota asociada al estado.", example = "123")
    @Column(name = "id_mascota", nullable = false)
    private Integer idMascota;

    @Schema(description = "Nombre del estado de la mascota.", example = "Saludable")
    @Column( nullable = false)
    private String estadoMascota;

    @Schema(description = "Fecha en la que se registró el estado de la mascota.", example = "2024-06-15")
    @Column( nullable = false)
    private Date fechaEstado;

    @Schema(description = "Motivo del cambio de estado de la mascota.", example = "Vacunación completada")
    @Column(nullable = false)
    private String motivoCambio;
   

}
