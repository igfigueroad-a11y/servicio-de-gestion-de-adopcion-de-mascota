package cl.duoc.mscontrolpostadopcion.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.mscontrolpostadopcion.model.Visita;
import cl.duoc.mscontrolpostadopcion.repository.VisitaRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(VisitaRepository controlRepo) {
        return args -> {
            if (controlRepo.count() == 0) {
                Visita v1 = new Visita(null, 1, new java.util.Date(), "Buena", "El perro se ve saludable y feliz");
                Visita v2 = new Visita(null, 1, new java.util.Date(), "Regular", "Se recomienda reforzar rutina de paseos y control veterinario");
                Visita v3 = new Visita(null, 2, new java.util.Date(), "Buena", "El gato se encuentra adaptado al hogar y en buen estado");
                Visita v4 = new Visita(null, 2, new java.util.Date(), "Mala", "El gato parece estar enfermo y necesita atención veterinaria urgente");
                Visita v5 = new Visita(null, 3, new java.util.Date(), "Regular", "La mascota presenta nerviosismo, pero responde bien al nuevo entorno");
                Visita v6 = new Visita(null, 4, new java.util.Date(), "Buena", "La familia demuestra compromiso y buen manejo de cuidados");
                Visita v7 = new Visita(null, 5, new java.util.Date(), "Regular", "El espacio es adecuado, pero faltan algunas medidas de seguridad");
                Visita v8 = new Visita(null, 6, new java.util.Date(), "Buena", "La mascota mantiene controles al día y buena convivencia");
                Visita v9 = new Visita(null, 7, new java.util.Date(), "Mala", "Se detectaron condiciones poco favorables para el bienestar de la mascota");
                Visita v10 = new Visita(null, 8, new java.util.Date(), "Regular", "La adopción sigue en observación con evolución aceptable");

                controlRepo.save(v1);
                controlRepo.save(v2);
                controlRepo.save(v3);
                controlRepo.save(v4);
                controlRepo.save(v5);
                controlRepo.save(v6);
                controlRepo.save(v7);
                controlRepo.save(v8);
                controlRepo.save(v9);
                controlRepo.save(v10);

                System.out.println("Datos de visitas cargados correctamente");
            }
        };
    }
}