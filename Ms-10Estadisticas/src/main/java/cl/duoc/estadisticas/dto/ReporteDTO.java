package cl.duoc.estadisticas.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporteDTO {
    private Integer id_reporte;
    private String nombre;
    private String tipoReporte;
    private Date fechaCreacion;
    private Date fInicio;
    private Date fFin;
    private String resultado;


}
