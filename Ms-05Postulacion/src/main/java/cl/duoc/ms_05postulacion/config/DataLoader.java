package cl.duoc.ms_05postulacion.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.ms_05postulacion.model.Postulacion;
import cl.duoc.ms_05postulacion.repository.PostulacionRepository;
@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(PostulacionRepository postulacionRepo) {
        return args -> {
            if (postulacionRepo.count() == 0) {
                Postulacion p1 = new Postulacion(null, 1, 1, LocalDateTime.of(2026, 5, 10, 10, 30), "Quiero darle un hogar estable y responsable", true, "Casa", true, "PENDIENTE", null);
                Postulacion p2 = new Postulacion(null, 2, 2, LocalDateTime.of(2026, 5, 11, 12, 15), "Busco compania para mi departamento", false, "Departamento", false, "EN_REVISION", null);
                Postulacion p3 = new Postulacion(null, 3, 3, LocalDateTime.of(2026, 5, 12, 9, 45), "Tengo experiencia previa adoptando perros", true, "Casa", true, "APROBADA", LocalDateTime.of(2026, 5, 14, 16, 0));
                Postulacion p4 = new Postulacion(null, 4, 4, LocalDateTime.of(2026, 5, 13, 14, 20), "Mi familia quiere integrar una mascota pequeña", false, "Casa", true, "RECHAZADA", LocalDateTime.of(2026, 5, 15, 11, 30));
                Postulacion p5 = new Postulacion(null, 5, 5, LocalDateTime.of(2026, 5, 14, 17, 10), "Me gustan mucho las aves y tengo espacio adecuado", true, "Departamento", false, "APROBADA", LocalDateTime.of(2026, 5, 16, 10, 45));
                Postulacion p6 = new Postulacion(null, 6, 6, LocalDateTime.of(2026, 5, 15, 8, 55), "Quiero cuidar una mascota con dedicacion diaria", false, "Casa", true, "PENDIENTE", null);
                Postulacion p7 = new Postulacion(null, 7, 7, LocalDateTime.of(2026, 5, 16, 19, 5), "Tengo tiempo libre y deseo adoptar una mascota pequena", true, "Departamento", false, "EN_REVISION", null);
                Postulacion p8 = new Postulacion(null, 8, 8, LocalDateTime.of(2026, 5, 17, 13, 40), "Siempre quise tener una tortuga y puedo mantener su habitat", false, "Casa", true, "PENDIENTE", null);
                postulacionRepo.save(p1);
                postulacionRepo.save(p2);
                postulacionRepo.save(p3);
                postulacionRepo.save(p4);
                postulacionRepo.save(p5);
                postulacionRepo.save(p6);
                postulacionRepo.save(p7);
                postulacionRepo.save(p8);
                System.out.println("Datos de postulacion cargados correctamente");
            }
        };
    }
}
