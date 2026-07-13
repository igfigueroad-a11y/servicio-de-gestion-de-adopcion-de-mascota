package cl.duoc.mensajes.config;



import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.mensajes.model.Aviso;
import cl.duoc.mensajes.repository.AvisoRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(AvisoRepository avisoRepo) {
        return args -> {
            if (avisoRepo.count() == 0) {
                Aviso a1 = new Aviso(null, 1, "EMAIL", "Postulación recibida", "Tu postulación para la mascota fue registrada correctamente.", new java.util.Date(), "POSTULACION", 1);
                Aviso a2 = new Aviso(null, 2, "EMAIL", "Postulación en revisión", "Tu solicitud está siendo revisada por el equipo de adopción.", new java.util.Date(), "POSTULACION", 2);
                Aviso a3 = new Aviso(null, 3, "SMS", "Entrevista agendada", "Se agendó una entrevista para continuar con tu proceso de adopción.", new java.util.Date(), "PROCESO_ADOPCION", 3);
                Aviso a4 = new Aviso(null, 4, "EMAIL", "Documentación pendiente", "Debes completar la documentación requerida para seguir con el proceso.", new java.util.Date(), "PROCESO_ADOPCION", 4);
                Aviso a5 = new Aviso(null, 5, "SMS", "Solicitud pendiente", "Tu solicitud fue ingresada y está pendiente de evaluación.", new java.util.Date(), "POSTULACION", 5);
                Aviso a6 = new Aviso(null, 6, "EMAIL", "Adopción completada", "Tu proceso de adopción fue completado exitosamente.", new java.util.Date(), "ADOPCION", 6);
                Aviso a7 = new Aviso(null, 7, "EMAIL", "Postulación rechazada", "Tu postulación no fue aprobada en esta oportunidad.", new java.util.Date(), "POSTULACION", 7);
                Aviso a8 = new Aviso(null, 8, "SMS", "Seguimiento programado", "Se programó una visita de seguimiento posterior a la adopción.", new java.util.Date(), "CONTROL_POST_ADOPCION", 8);

                avisoRepo.save(a1);
                avisoRepo.save(a2);
                avisoRepo.save(a3);
                avisoRepo.save(a4);
                avisoRepo.save(a5);
                avisoRepo.save(a6);
                avisoRepo.save(a7);
                avisoRepo.save(a8);

                System.out.println("Datos de mensajes cargados correctamente");
            }
        };
    }
}