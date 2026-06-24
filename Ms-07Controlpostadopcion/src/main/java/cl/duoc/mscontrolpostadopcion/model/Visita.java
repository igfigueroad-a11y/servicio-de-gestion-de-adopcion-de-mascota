package cl.duoc.mscontrolpostadopcion.model;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visitas")

@Schema(description = "Entidad que representa una visita de seguimiento a una mascota adoptada.")
public class Visita {

    @Schema(description = "Identificador único de la visita.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_visita;

    @Schema(description = "Identificador de la adopción asociada a la visita.")  
    @Column(name="id_adopcion", nullable = false)
    private Integer id_adopcion;

    @Schema(description = "Fecha de la visita.")
    @Column(nullable=false)
    private Date fecha_visita;
    
    @Schema(description = "Estado de la mascota observado durante la visita.")
    @Column(nullable=false)
    private String estado_mascota_observado;
    
    @Schema(description = "Descripción de la visita.")
    @Column(nullable=false)
    private String descripcion_visita;

        
    }

    






