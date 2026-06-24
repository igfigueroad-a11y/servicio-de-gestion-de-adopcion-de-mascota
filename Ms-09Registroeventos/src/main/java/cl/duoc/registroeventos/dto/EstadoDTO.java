package cl.duoc.registroeventos.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {
    private Integer id;
    private String estadoMascota;
    private Date fechaEstado;
    private String motivoCambio;    

}
