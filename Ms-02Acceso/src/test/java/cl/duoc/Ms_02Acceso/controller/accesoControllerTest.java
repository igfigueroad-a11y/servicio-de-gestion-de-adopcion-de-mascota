package cl.duoc.Ms_02Acceso.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import cl.duoc.Ms_02Acceso.dto.AccesoDto;
import cl.duoc.Ms_02Acceso.model.Acceso;
import cl.duoc.Ms_02Acceso.service.AccesoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccesoController.class)
public class accesoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccesoService acs;

    private Acceso acceso;
    private AccesoDto accesoDto;

    @BeforeEach
    void setUp() {
        acceso = new Acceso();
        acceso.setIdCuenta(1);
        acceso.setIdUsuario(10);
        acceso.setNombreUsuario("jperez");
        acceso.setContraseña("1234");
        acceso.setCuentaActiva(true);

        accesoDto = new AccesoDto();
        accesoDto.setIdCuenta(1);
        accesoDto.setIdUsuario(10);
        accesoDto.setNombreUsuario("jperez");
        accesoDto.setCuentaActiva(true);
    }

    @Test
    void listarCuentas_retorna200() throws Exception {
        when(acs.listarCuentas()).thenReturn(Arrays.asList(accesoDto));

        mockMvc.perform(get("/api/v1/acceso"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        when(acs.buscarPorId(1)).thenReturn(accesoDto);

        mockMvc.perform(get("/api/v1/acceso/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorNombreUsuario_retorna200() throws Exception {
        when(acs.buscarPorNombreUsuario("jperez")).thenReturn(accesoDto);

        mockMvc.perform(get("/api/v1/acceso/usuario/jperez"))
                .andExpect(status().isOk());
    }

    @Test
    void crearAcceso_retorna201() throws Exception {
        when(acs.crearAcceso(any(Acceso.class))).thenReturn(accesoDto);

        mockMvc.perform(post("/api/v1/acceso")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "idUsuario": 10,
                          "nombreUsuario": "jperez",
                          "contraseña": "1234",
                          "cuentaActiva": true
                        }
                        """))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizarAcceso_retorna200() throws Exception {
        when(acs.actualizarAccesoDto(any(Integer.class), any(Acceso.class))).thenReturn(accesoDto);

        mockMvc.perform(put("/api/v1/acceso/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "idUsuario": 10,
                          "nombreUsuario": "jperez",
                          "contraseña": "abcd",
                          "cuentaActiva": true
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarAcceso_retorna200_yMensaje() throws Exception {
        doNothing().when(acs).eliminarAcceso(1);

        mockMvc.perform(delete("/api/v1/acceso/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cuenta eliminada correctamente"));
    }
}