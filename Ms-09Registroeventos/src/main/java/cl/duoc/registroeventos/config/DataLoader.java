package cl.duoc.registroeventos.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.registroeventos.model.Estado;
import cl.duoc.registroeventos.model.Evento;
import cl.duoc.registroeventos.repository.EstadoRepository;
import cl.duoc.registroeventos.repository.EventoRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(EstadoRepository estadoRepo, EventoRepository eventoRepo) {
        return args -> {
            if (estadoRepo.count() == 0) {
                Estado e1 = new Estado(null, 1, "Saludable", new java.util.Date(), "Ingreso inicial al sistema");
                Estado e2 = new Estado(null, 2, "En observacion", new java.util.Date(), "Control veterinario preventivo");
                Estado e3 = new Estado(null, 3, "Recuperacion", new java.util.Date(), "Tratamiento por afección menor");
                Estado e4 = new Estado(null, 4, "Saludable", new java.util.Date(), "Vacunación completada");
                Estado e5 = new Estado(null, 5, "Pendiente evaluacion", new java.util.Date(), "Revisión médica programada");
                Estado e6 = new Estado(null, 6, "Saludable", new java.util.Date(), "Apta para adopción");
                Estado e7 = new Estado(null, 7, "Bajo tratamiento", new java.util.Date(), "Se indicó seguimiento veterinario");
                Estado e8 = new Estado(null, 8, "En observacion", new java.util.Date(), "Adaptación posterior a rescate");

                estadoRepo.save(e1);
                estadoRepo.save(e2);
                estadoRepo.save(e3);
                estadoRepo.save(e4);
                estadoRepo.save(e5);
                estadoRepo.save(e6);
                estadoRepo.save(e7);
                estadoRepo.save(e8);
            }

            if (eventoRepo.count() == 0) {
                Evento ev1 = new Evento(null, 1, 1, 1, "POSTULACION", "Se registró la postulación del usuario para la mascota", new java.util.Date());
                Evento ev2 = new Evento(null, 2, 2, 2, "REVISION", "La postulación fue enviada a revisión", new java.util.Date());
                Evento ev3 = new Evento(null, 3, 3, 3, "ENTREVISTA", "Se realizó entrevista con el postulante", new java.util.Date());
                Evento ev4 = new Evento(null, 4, 4, 4, "DOCUMENTACION", "Se solicitó documentación complementaria", new java.util.Date());
                Evento ev5 = new Evento(null, 5, 5, 5, "PENDIENTE", "La solicitud quedó pendiente de evaluación", new java.util.Date());
                Evento ev6 = new Evento(null, 6, 6, 6, "ADOPCION_COMPLETADA", "La adopción fue completada correctamente", new java.util.Date());
                Evento ev7 = new Evento(null, 7, 7, 7, "RECHAZO", "La postulación fue rechazada por incumplimiento de requisitos", new java.util.Date());
                Evento ev8 = new Evento(null, 8, 8, 8, "SEGUIMIENTO", "Se programó seguimiento posterior a la adopción", new java.util.Date());

                eventoRepo.save(ev1);
                eventoRepo.save(ev2);
                eventoRepo.save(ev3);
                eventoRepo.save(ev4);
                eventoRepo.save(ev5);
                eventoRepo.save(ev6);
                eventoRepo.save(ev7);
                eventoRepo.save(ev8);
            }

            System.out.println("Datos de estados y eventos cargados correctamente");
        };
    }
}