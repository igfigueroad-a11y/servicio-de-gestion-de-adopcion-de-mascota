package cl.duoc.msprocesoadopcion.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import cl.duoc.msprocesoadopcion.model.Adopcion;
import cl.duoc.msprocesoadopcion.repository.AdopcionRepository;

public class DataLoader {
@Bean
    CommandLineRunner initData(AdopcionRepository adopcionRepo) {
        return args -> {
            if (adopcionRepo.count() == 0) {
     Adopcion a1 = new Adopcion(null,  null, null, null, null, null, "COMPLETADA", "Entrega realizada con compromiso firmado");
                Adopcion a2 = new Adopcion(null,  null, null, null, null, null, "COMPLETADA", "Proceso finalizado sin observaciones");
                Adopcion a3 = new Adopcion(null,  null, null, null, null, null, "EN_PROCESO", "Pendiente firma de documentacion");
                Adopcion a4 = new Adopcion(null,  null, null, null, null, null, "EN_REVISION", "Validacion de antecedentes en curso");
                Adopcion a5 = new Adopcion(null,  null, null, null, null, null, "PENDIENTE", "Esperando confirmacion del adoptante");
                Adopcion a6 = new Adopcion(null,  null, null, null, null, null, "COMPLETADA", "Mascota entregada correctamente");
                Adopcion a7 = new Adopcion(null,  null, null, null, null, null, "RECHAZADA", "No cumple requisitos del proceso");
                Adopcion a8 = new Adopcion(null,  null, null, null, null, null, "EN_PROCESO", "Seguimiento inicial programado");

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



