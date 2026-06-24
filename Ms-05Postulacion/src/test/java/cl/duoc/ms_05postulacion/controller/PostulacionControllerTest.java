package cl.duoc.ms_05postulacion.controller;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import cl.duoc.ms_05postulacion.dto.PostulacionDto;
import cl.duoc.ms_05postulacion.model.Postulacion;
import cl.duoc.ms_05postulacion.service.PostulacionService;

@WebMvcTest(PostulacionController.class)
public class PostulacionControllerTest {

    @Autowired
    private MockMvc llamadaFalsa;

    @MockBean
    private PostulacionService ps;

    private Postulacion postulacion;
    private PostulacionDto postulacionDto;

    @BeforeEach
    void setUp() {
        postulacion = new Postulacion();
        postulacion.setIdPostulacion(1);
        postulacion.setIdUsuario(10);
        postulacion.setIdMascota(5);
        postulacion.setEstado("pendiente");
        postulacion.setMotivoAdopcion("Me gustan los perros");
        postulacion.setTieneMascotasPrevias(false);
        postulacion.setTipoVivienda("casa");
        postulacion.setTienePatio(true);

        postulacionDto = new PostulacionDto();
        postulacionDto.setIdPostulacion(1);
        postulacionDto.setIdUsuario(10);
        postulacionDto.setIdMascota(5);
        postulacionDto.setEstado("pendiente");
        postulacionDto.setMotivoAdopcion("Me gustan los perros");
        postulacionDto.setTieneMascotasPrevias(false);
        postulacionDto.setTipoVivienda("casa");
        postulacionDto.setTienePatio(true);
    }

    @Test
    void listarPostulaciones_retorna200() throws Exception {
        List<PostulacionDto> lista = new ArrayList<>();
        lista.add(postulacionDto);

        when(ps.ListarPostulaciones()).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/postulaciones"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        when(ps.BuscarPorId(1)).thenReturn(postulacionDto);

        llamadaFalsa.perform(get("/api/v1/postulaciones/1"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorUsuario_retorna200() throws Exception {
        List<PostulacionDto> lista = new ArrayList<>();
        lista.add(postulacionDto);

        when(ps.BuscarPorUsuario(10)).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/postulaciones/usuario/10"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorEstado_retorna200() throws Exception {
        List<PostulacionDto> lista = new ArrayList<>();
        lista.add(postulacionDto);

        when(ps.BuscarPorEstado("pendiente")).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/postulaciones/estado/pendiente"))
                .andExpect(status().isOk());
    }

    @Test
    void guardarPostulacion_retorna201() throws Exception {
        when(ps.GuardarPostulacion(any(Postulacion.class))).thenReturn(postulacionDto);

        llamadaFalsa.perform(post("/api/v1/postulaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "idUsuario": 10,
                          "idMascota": 5,
                          "motivoAdopcion": "Me gustan los perros",
                          "tieneMascotasPrevias": false,
                          "tipoVivienda": "casa",
                          "tienePatio": true
                        }
                        """))
                .andExpect(status().isCreated());
    }

    @Test
    void cambiarEstado_retorna200() throws Exception {
        when(ps.CambiarEstado(1, "aprobado")).thenReturn(postulacionDto);

        llamadaFalsa.perform(put("/api/v1/postulaciones/1/estado")
                .param("estado", "aprobado"))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarPostulacion_retorna204() throws Exception {
        doNothing().when(ps).EliminarPostulacion(1);

        llamadaFalsa.perform(delete("/api/v1/postulaciones/1"))
                .andExpect(status().isNoContent());
    }
}