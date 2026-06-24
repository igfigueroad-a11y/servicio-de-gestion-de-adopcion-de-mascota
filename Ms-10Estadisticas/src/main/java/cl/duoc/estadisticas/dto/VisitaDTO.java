package cl.duoc.estadisticas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitaDTO {
    private Integer id_visita;
    private String fecha_visita;
    private String estado_mascota_observado;
    private String descripcion_visita;
    private Integer visita_programada_id;


}
