package cl.duoc.ms_mascotas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import cl.duoc.ms_mascotas.dto.MascotaDto;
import cl.duoc.ms_mascotas.model.Mascota;
import cl.duoc.ms_mascotas.service.MascotaService;

@WebMvcTest(MascotaController.class)
public class mascotaControllerTest {

    @Autowired
    private MockMvc llamadaFalsa;

    @MockBean
    private MascotaService ms;

    private Mascota mascota;
    private MascotaDto mascotaDto;

    @BeforeEach
    void setUp() {
        mascota = new Mascota();
        mascota.setIdMascota(1);
        mascota.setNombreMascota("Firulais");
        mascota.setEspecieMascota("Perro");

        mascotaDto = new MascotaDto();
        mascotaDto.setIdMascota(1);
        mascotaDto.setNombreMascota("Firulais");
        mascotaDto.setEspecieMascota("Perro");
    }

    @Test
    void listarMascotas_retorna200() throws Exception {
        List<MascotaDto> lista = new ArrayList<>();
        lista.add(mascotaDto);

        when(ms.ListarMascotas()).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/mascotas"))
                .andExpect(status().isOk());
    }

    @Test
    void guardarMascota_retorna201() throws Exception {
        when(ms.GuardarMascota(any(Mascota.class))).thenReturn(mascotaDto);

        llamadaFalsa.perform(post("/api/v1/mascotas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                          "idMascota": 1,
                          "nombreMascota": "Firulais",
                          "especieMascota": "Perro"
                        }
                        """))
                .andExpect(status().isCreated());
    }

    @Test
    void buscarPorId_retorna200() throws Exception {
        when(ms.BuscarPorId(1)).thenReturn(mascotaDto);

        llamadaFalsa.perform(get("/api/v1/mascotas/1"))
                .andExpect(status().isOk());
    }

    @Test
    void filtrarPorEspecie_retorna200() throws Exception {
        List<MascotaDto> lista = new ArrayList<>();
        lista.add(mascotaDto);

        when(ms.FiltrarPorEspecie("Perro")).thenReturn(lista);

        llamadaFalsa.perform(get("/api/v1/mascotas/especie/Perro"))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarMascota_retorna204() throws Exception {
        doNothing().when(ms).EliminarMascota(1);

        llamadaFalsa.perform(delete("/api/v1/mascotas/1"))
                .andExpect(status().isNoContent());
    }
}