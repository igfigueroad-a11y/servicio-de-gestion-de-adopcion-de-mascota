package cl.duoc.ms_mascotas.config;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cl.duoc.ms_mascotas.model.Mascota;
import cl.duoc.ms_mascotas.repository.MascotaRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initMascotasData(MascotaRepository mascotaRepo) {
        return args -> {
            if (mascotaRepo.count() == 0) {
                Mascota m1 = new Mascota(null, "Bruno", 1, "Perro", "Labrador", 5, "Muy amigable con ninos", "Disponible", LocalDate.of(2026, 4, 1), null);
                Mascota m2 = new Mascota(null, "Nina", 2, "Gato", "Persa", 3, "Calmada y carinosa", "Disponible", LocalDate.of(2026, 4, 3), null);
                Mascota m3 = new Mascota(null, "Max", 1, "Perro", "Quiltro", 2, "Activo y obediente", "Disponible", LocalDate.of(2026, 4, 6), null);
                Mascota m4 = new Mascota(null, "Pelusa", 3, "Conejo", "Cabeza de leon", 1, "Pequena y juguetona", "Disponible", LocalDate.of(2026, 4, 8), null);
                Mascota m5 = new Mascota(null, "Kiwi", 4, "Ave", "Periquito", 2, "Muy sociable y canta bastante", "Adoptada", LocalDate.of(2026, 3, 29), null);
                Mascota m6 = new Mascota(null, "Loki", 5, "Huron", "Domestico", 4, "Curioso y agil", "Disponible", LocalDate.of(2026, 4, 12), null);
                Mascota m7 = new Mascota(null, "Chispa", 6, "Hamster", "Sirio", 1, "Ideal para departamento", "Disponible", LocalDate.of(2026, 4, 14), null);
                Mascota m8 = new Mascota(null, "Tuga", 7, "Tortuga", "Orejas rojas", 6, "De caracter tranquilo", "En tratamiento", LocalDate.of(2026, 4, 18), null);

                mascotaRepo.save(m1);
                mascotaRepo.save(m2);
                mascotaRepo.save(m3);
                mascotaRepo.save(m4);
                mascotaRepo.save(m5);
                mascotaRepo.save(m6);
                mascotaRepo.save(m7);
                mascotaRepo.save(m8);

                System.out.println("Datos de mascotas cargados correctamente");
            }
        };
    }
}