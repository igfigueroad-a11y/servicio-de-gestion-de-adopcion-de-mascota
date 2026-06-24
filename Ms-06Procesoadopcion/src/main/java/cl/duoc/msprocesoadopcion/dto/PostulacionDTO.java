package cl.duoc.msprocesoadopcion.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostulacionDTO {
    private Integer idPostulacion;
    private Date fechaPostulacion;
    private String motivoAdopcion;
    private Boolean tieneMascotasPrevias;
    private String tipoVivienda;
    private Boolean tienePatio;
    private String estado;
    private Date fechaRespuesta;


}
