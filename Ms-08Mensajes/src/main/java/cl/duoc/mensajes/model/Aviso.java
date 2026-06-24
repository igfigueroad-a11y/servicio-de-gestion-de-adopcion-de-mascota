package cl.duoc.mensajes.model;

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
@Table(name = "mensajes")

@Schema(description = "Clase que representa un aviso o mensaje enviado a un usuario.")
public class Aviso {

    @Schema(description = "Identificador único del aviso.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id;

    @Schema(description = "Identificador del usuario que envía el aviso.")
    @Column(name = "id_usuario_destino",     nullable = false)
    private Integer id_usuario_destino;                 
    @Schema(description = "Canal de envío del aviso.")
    @Column(nullable = false)               
    private String canalEnvio;
    @Schema(description = "Asunto del aviso.")
    @Column(nullable = false)
    private String asunto;
    @Schema(description = "Mensaje del aviso.")
    @Column(nullable = false)
    private String mensaje;
    @Schema(description = "Fecha de envío del aviso.")
    @Column(nullable = false)
    private Date fechaEnvio;
    @Schema(description = "Tipo de evento del aviso.")
    @Column(nullable = false)
    private String tipoEvento;
    @Schema(description = "Identificador del evento relacionado con el aviso.")
    @Column(nullable = false)
    private Integer id_evento_relacionado;
    
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

}
