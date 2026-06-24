package cl.duoc.registroeventos.model;
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

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventos")
@Schema(description = "Entidad que representa un evento relacionado con una adopción de mascota.")
public class Evento {

    @Schema(description = "Identificador único del evento.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_evento;

    @Schema(description = "Identificador de la adopción asociada al evento.")
    @Column(name = "id_adopcion", nullable = false)
    private Integer idAdopcion;
    @Schema(description = "Identificador del usuario asociado al evento.")
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;
    @Schema(description = "Identificador de la mascota asociada al evento.")
    @Column(name = "id_mascota", nullable = false)
    private Integer id_mascota;

    @Schema(description = "Tipo de evento relacionado con la adopción de la mascota.")
    @Column(nullable = false)
    private String tipoEvento;

    @Schema(description = "Descripción detallada del evento.")
    @Column(nullable = false)
    private String descripcionEvento;    
    @Schema(description = "Fecha y hora en la que ocurrió el evento.")
    @Column(nullable = false)
    private Date fechaEvento;

}
