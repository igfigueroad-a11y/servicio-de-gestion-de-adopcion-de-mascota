package cl.duoc.ms_mascotas.dto;
import java.time.LocalDate;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class MascotaDto {
private Integer IdMascota;
private Integer IdTipoMascota;
private String NombreMascota;
private String EspecieMascota;
private String Raza;
private Integer Edad;
private String Descripcion;
private String Disponibilidad;
private LocalDate FechaIngreso;
}
