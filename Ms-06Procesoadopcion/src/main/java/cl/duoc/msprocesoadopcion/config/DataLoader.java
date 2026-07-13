package cl.duoc.msprocesoadopcion.config;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.msprocesoadopcion.model.Adopcion;
import cl.duoc.msprocesoadopcion.repository.AdopcionRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(AdopcionRepository adopcionRepo) {
        return args -> {
            if (adopcionRepo.count() == 0) {
                Adopcion a1 = new Adopcion(null, 1, 1, 1, Date.valueOf("2026-03-10"), "COMPLETADA", "Adopción finalizada sin observaciones");
                Adopcion a2 = new Adopcion(null, 2, 2, 2, Date.valueOf("2026-03-15"), "COMPLETADA", "Proceso completado correctamente");
                Adopcion a3 = new Adopcion(null, 3, 3, 3, Date.valueOf("2026-04-01"), "EN_PROCESO", "Entrevista y validación en curso");
                Adopcion a4 = new Adopcion(null, 4, 4, 4, Date.valueOf("2026-04-08"), "EN_REVISION", "Documentación pendiente de revisión final");
                Adopcion a5 = new Adopcion(null, 5, 5, 5, Date.valueOf("2026-04-20"), "PENDIENTE", "Solicitud ingresada, esperando evaluación");
                Adopcion a6 = new Adopcion(null, 6, 6, 6, Date.valueOf("2026-05-02"), "COMPLETADA", "Adopción aprobada y entrega realizada");
                Adopcion a7 = new Adopcion(null, 7, 7, 7, Date.valueOf("2026-05-10"), "RECHAZADA", "Postulación rechazada por incumplimiento de requisitos");
                Adopcion a8 = new Adopcion(null, 8, 8, 8, Date.valueOf("2026-05-18"), "EN_PROCESO", "Visita domiciliaria programada");

                adopcionRepo.save(a1);
                adopcionRepo.save(a2);
                adopcionRepo.save(a3);
                adopcionRepo.save(a4);
                adopcionRepo.save(a5);
                adopcionRepo.save(a6);
                adopcionRepo.save(a7);
                adopcionRepo.save(a8);

                System.out.println("Datos de adopcion cargados correctamente");
            }
        };
    }
}