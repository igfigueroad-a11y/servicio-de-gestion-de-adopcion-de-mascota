package cl.duoc.Ms_02Acceso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AccesoDto {

private Integer idCuenta;
private Integer idUsuario;
private String nombreUsuario;
private Boolean cuentaActiva;
public void setIdAcceso(int i) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setIdAcceso'");
}
public void setContrasena(String string) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setContrasena'");
}





}
