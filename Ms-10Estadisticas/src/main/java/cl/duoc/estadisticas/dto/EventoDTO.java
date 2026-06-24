package cl.duoc.estadisticas.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoDTO {

    private Integer id_evento;
    private String tipoEvento;
    private String descripcionEvento;
    private Date fechaEvento;

}
