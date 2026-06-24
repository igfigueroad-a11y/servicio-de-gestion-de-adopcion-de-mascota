package cl.duoc.registroeventos.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdopcionDTO {
    private Integer id_adopcion;
    private Date fecha_adopcion;
    private String estado_adopcion;
    private String notas_adicionales;

}
