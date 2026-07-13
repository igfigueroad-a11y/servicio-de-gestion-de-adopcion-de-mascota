package cl.duoc.estadisticas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.estadisticas.model.Reporte;
import cl.duoc.estadisticas.repository.ReporteRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(ReporteRepository reporteRepo) {
        return args -> {
            if (reporteRepo.count() == 0) {
                Reporte r1 = new Reporte(null, 1, 1, 1, 1, 1, 1, "Reporte de postulacion 1", "POSTULACION", new java.util.Date(), new java.util.Date(), new java.util.Date(), "Postulación registrada correctamente");
                Reporte r2 = new Reporte(null, 2, 2, 2, 2, 2, 2, "Reporte de postulacion 2", "REVISION", new java.util.Date(), new java.util.Date(), new java.util.Date(), "Solicitud en proceso de revisión");
                Reporte r3 = new Reporte(null, 3, 3, 3, 3, 3, 3, "Reporte de adopcion 3", "ADOPCION", new java.util.Date(), new java.util.Date(), new java.util.Date(), "Entrevista realizada y proceso en curso");
                Reporte r4 = new Reporte(null, 4, 4, 4, 4, 4, 4, "Reporte de documentos 4", "DOCUMENTACION", new java.util.Date(), new java.util.Date(), new java.util.Date(), "Falta documentación complementaria");
                Reporte r5 = new Reporte(null, 5, 5, 5, 5, 5, 5, "Reporte pendiente 5", "PENDIENTE", new java.util.Date(), new java.util.Date(), new java.util.Date(), "Caso pendiente de evaluación");
                Reporte r6 = new Reporte(null, 6, 6, 6, 6, 6, 6, "Reporte final 6", "ADOPCION_COMPLETADA", new java.util.Date(), new java.util.Date(), new java.util.Date(), "Adopción completada exitosamente");
                Reporte r7 = new Reporte(null, 7, 7, 7, 7, 7, 7, "Reporte rechazo 7", "RECHAZO", new java.util.Date(), new java.util.Date(), new java.util.Date(), "Postulación rechazada por incumplimiento");
                Reporte r8 = new Reporte(null, 8, 8, 8, 8, 8, 8, "Reporte seguimiento 8", "SEGUIMIENTO", new java.util.Date(), new java.util.Date(), new java.util.Date(), "Seguimiento post adopción programado");

                reporteRepo.save(r1);
                reporteRepo.save(r2);
                reporteRepo.save(r3);
                reporteRepo.save(r4);
                reporteRepo.save(r5);
                reporteRepo.save(r6);
                reporteRepo.save(r7);
                reporteRepo.save(r8);

                System.out.println("Datos de reportes cargados correctamente");
            }
        };
    }
}