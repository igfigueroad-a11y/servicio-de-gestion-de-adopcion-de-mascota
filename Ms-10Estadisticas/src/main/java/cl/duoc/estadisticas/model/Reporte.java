package cl.duoc.estadisticas.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reportes")

@Schema(description = "Entidad que representa un reporte en la base de datos.")
public class Reporte {

    @Schema(description = "Identificador único del reporte.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id_reporte;

    @Schema(description = "Identificador del usuario asociado al reporte.")
    @Column (name = "idUsuario", nullable = false)
    private Integer idUsuario;  
    @Schema(description = "Identificador de la postulación asociada al reporte.")
    @Column(name = "idPostulacion", nullable = false)
    private Integer idPostulacion;                                  
    @Schema(description = "Identificador de la mascota asociada al reporte.")
    @Column(name = "id_mascota", nullable = false)
    private Integer id_mascota;
    @Schema(description = "Identificador de la adopción asociada al reporte.")
    @Column(name = "id_adopcion", nullable = false)
    private Integer id_adopcion;
    @Schema(description = "Identificador de la visita asociada al reporte.")
    @Column(name = "id_visita", nullable = false)
    private Integer id_visita;
    @Schema(description = "Identificador del evento asociado al reporte.")
    @Column(name = "id_evento", nullable = false) 
    private Integer id_evento;                          

    @Schema(description = "Nombre del reporte.")
    @Column(nullable = false)                        
    private String nombre;
    @Schema(description = "Tipo de reporte.")
    @Column(nullable = false)
    private String tipoReporte;
    @Schema(description = "Fecha de creación del reporte.")
    @Column(nullable = false)
    private Date fechaCreacion;
    @Schema(description = "Fecha de inicio del reporte.")
    @Column(nullable = false)
    private Date fInicio;
    @Schema(description = "Fecha de fin del reporte.")
    @Column(nullable = false)
    private Date fFin;
    @Schema(description = "Resultado del reporte.")
    @Column(nullable = false)
    private String resultado;

}
