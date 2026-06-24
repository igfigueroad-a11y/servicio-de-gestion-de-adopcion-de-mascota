package cl.duoc.ms_mascotas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.ms_mascotas.model.SaludMascota;
import cl.duoc.ms_mascotas.repository.SaludMascotaRepository;
@Configuration
public class DataLoader1 {
  @Bean
    CommandLineRunner initData(SaludMascotaRepository saludRepo) {
        return args -> {
            if (saludRepo.count() == 0) {
 SaludMascota s1 = new SaludMascota(null, null, 1, true, true, true, "Excelente", "2026-05-02", "Sin observaciones");
                SaludMascota s2 = new SaludMascota(null, null, 2, true, true, true, "Muy buena", "2026-05-04", "Control anual al dia");
                SaludMascota s3 = new SaludMascota(null, null, 3, true, false, true, "Buena", "2026-05-06", "Esterilizacion pendiente");
                SaludMascota s4 = new SaludMascota(null, null, 4, false, false, true, "Estable", "2026-05-08", "Debe reforzar vacunas");
                SaludMascota s5 = new SaludMascota(null, null, 5, true, false, true, "Buena", "2026-05-10", "Revision reciente");
                SaludMascota s6 = new SaludMascota(null, null, 6, true, true, false, "Buena", "2026-05-11", "Desparasitacion pendiente");
                SaludMascota s7 = new SaludMascota(null, null, 7, false, false, true, "Muy buena", "2026-05-13", "Sin problemas de salud");
                SaludMascota s8 = new SaludMascota(null, null, 8, true, false, false, "En observacion", "2026-05-15", "Control veterinario mensual");
                saludRepo.save(s1);
                saludRepo.save(s2);
                saludRepo.save(s3);
                saludRepo.save(s4);
                saludRepo.save(s5);
                saludRepo.save(s6);
                saludRepo.save(s7);
                saludRepo.save(s8);
                System.out.println("Datos de salud mascota cargados correctamente");
            }
        };
    }
}
