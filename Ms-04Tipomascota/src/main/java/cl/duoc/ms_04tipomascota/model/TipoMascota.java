package cl.duoc.ms_04tipomascota.model;

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
@Table(name = "tipo_mascota")
@Data
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "Entidad que representa un tipo de mascota en la base de datos.")
public class TipoMascota {

    @Schema(description = "Identificador único del tipo de mascota")
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoMascota;

    @Schema(description = "Nombre del tipo de mascota")
    @Column(name = "nombre_tipo", nullable = false, unique = true)
    private String nombreTipo;

    @Schema(description = "Descripción del tipo de mascota")
    @Column(name = "descripcion")
    private String descripcion;

}

