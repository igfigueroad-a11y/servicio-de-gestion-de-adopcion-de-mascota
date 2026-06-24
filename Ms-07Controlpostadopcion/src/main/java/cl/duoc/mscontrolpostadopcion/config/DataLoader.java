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
                controlRepo.save(new Visita(null, 1, new java.util.Date(), "Buena", "El perro se ve saludable y feliz"));
                controlRepo.save(new Visita(null, 1, new java.util.Date(), "Regular", "El perro tiene un poco de sarna pero está en proceso de recuperación"));
                controlRepo.save(new Visita(null, 2, new java.util.Date(), "Mala", "El gato parece estar enfermo y necesita atención veterinaria urgente"));
            }
        };

}
}