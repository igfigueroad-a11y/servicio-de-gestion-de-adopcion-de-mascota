package cl.duoc.ms_04tipomascota.controller;

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

import cl.duoc.ms_04tipomascota.dto.TipoMascotaDto;
import cl.duoc.ms_04tipomascota.model.TipoMascota;
import cl.duoc.ms_04tipomascota.service.TipoMascotaService;

@WebMvcTest(TipoMascotaController.class)
public class TipoMascotaControllerTest {

    @Autowired
    private MockMvc llamadaFalsa;

    @MockBean
    private TipoMascotaService tpms;

    private TipoMascota tipoMascota;
    private TipoMascotaDto tipoMascotaDto;

    @BeforeEach
    void setUp() {
        tipoMascota = new TipoMascota();
        tipoMascota.setIdTipoMascota(1);
        tipoMascota.setNombreTipo("Perro");
        tipoMascota.setDescripcion("Mascota canina");

        tipoMascotaDto = new TipoMascotaDto();
        tipoMascotaDto.setIdTipoMascota(1);
        tipoMascotaDto.setNombreTipo("Perro");
        tipoMascotaDto.setDescripcion("Mascota canina");
    }

    @Test
    void listarTipos_retorna200() throws Exception {
        List<TipoMascotaDto> lista = new ArrayList<>();
        lista.add(tipoMascotaDto);

        when(tpms.ListarTipoMascota()).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/tipomascota"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        when(tpms.BuscarPorId(1)).thenReturn(tipoMascotaDto);

        llamadaFalsa.perform(get("/api/v1/tipomascota/1"))
                .andExpect(status().isOk());
    }

    @Test
    void guardarTipo_retorna201() throws Exception {
        when(tpms.GuardarTipo(any(TipoMascota.class))).thenReturn(tipoMascotaDto);

        llamadaFalsa.perform(post("/api/v1/tipomascota")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "idTipoMascota": 1,
                          "nombreTipo": "Perro",
                          "descripcion": "Mascota canina"
                        }
                        """))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizarTipo_retorna200() throws Exception {
        when(tpms.ActualizarTipo(any(Integer.class), any(TipoMascota.class))).thenReturn(tipoMascotaDto);

        llamadaFalsa.perform(put("/api/v1/tipomascota/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "idTipoMascota": 1,
                          "nombreTipo": "Perro",
                          "descripcion": "Mascota canina"
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarTipo_retorna204() throws Exception {
        doNothing().when(tpms).EliminarTipo(1);

        llamadaFalsa.perform(delete("/api/v1/tipomascota/1"))
                .andExpect(status().isNoContent());
    }
}