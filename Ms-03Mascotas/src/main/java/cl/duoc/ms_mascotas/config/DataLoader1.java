package cl.duoc.ms_mascotas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.ms_mascotas.model.Mascota;
import cl.duoc.ms_mascotas.model.SaludMascota;
import cl.duoc.ms_mascotas.repository.MascotaRepository;
import cl.duoc.ms_mascotas.repository.SaludMascotaRepository;

@Configuration
public class DataLoader1 {

    @Bean
    CommandLineRunner initSaludMascotasData(SaludMascotaRepository saludRepo, MascotaRepository mascotaRepo) {
        return args -> {
            if (saludRepo.count() == 0) {
                Mascota m1 = mascotaRepo.findById(1).orElse(null);
                Mascota m2 = mascotaRepo.findById(2).orElse(null);
                Mascota m3 = mascotaRepo.findById(3).orElse(null);
                Mascota m4 = mascotaRepo.findById(4).orElse(null);
                Mascota m5 = mascotaRepo.findById(5).orElse(null);
                Mascota m6 = mascotaRepo.findById(6).orElse(null);
                Mascota m7 = mascotaRepo.findById(7).orElse(null);
                Mascota m8 = mascotaRepo.findById(8).orElse(null);

                saludRepo.save(new SaludMascota(null, m1, true, true, true, "Excelente", "2026-05-02", "Sin observaciones"));
                saludRepo.save(new SaludMascota(null, m2, true, true, true, "Muy buena", "2026-05-04", "Control anual al dia"));
                saludRepo.save(new SaludMascota(null, m3, true, false, true, "Buena", "2026-05-06", "Esterilizacion pendiente"));
                saludRepo.save(new SaludMascota(null, m4, false, false, true, "Estable", "2026-05-08", "Debe reforzar vacunas"));
                saludRepo.save(new SaludMascota(null, m5, true, false, true, "Buena", "2026-05-10", "Revision reciente"));
                saludRepo.save(new SaludMascota(null, m6, true, true, false, "Buena", "2026-05-11", "Desparasitacion pendiente"));
                saludRepo.save(new SaludMascota(null, m7, false, false, true, "Muy buena", "Sin problemas de salud", "Sin problemas de salud"));
                saludRepo.save(new SaludMascota(null, m8, true, false, false, "En observacion", "2026-05-15", "Control veterinario mensual"));

                System.out.println("Datos de salud mascota cargados correctamente");
            }
        };
    }
}