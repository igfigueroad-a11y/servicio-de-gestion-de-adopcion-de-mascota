package cl.duoc.mensajes.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvisoDTO {

    private Integer id;
    private String canalEnvio;
    private String asunto;
    private String mensaje;
    private String tipoEvento;
    private Integer id_evento_relacionado;
    private Date fechaEnvio;

}
