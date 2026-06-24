package cl.duoc.Ms_02Acceso.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.Ms_02Acceso.model.Acceso;
import cl.duoc.Ms_02Acceso.repository.AccesoRepository;

@Configuration

public class DataLoader {
 @Bean
    CommandLineRunner initData(AccesoRepository accesoRepo) {
        return args -> {
            if (accesoRepo.count() == 0) {
                Acceso a1 = new Acceso(null, 1, "sebastian01", "Pass123", true);
                Acceso a2 = new Acceso(null, 2, "fernanda02", "Pass123", true);
                Acceso a3 = new Acceso(null, 3, "tomas03", "Clave456", true);
                Acceso a4 = new Acceso(null, 4, "daniela04", "Clave456", true);
                Acceso a5 = new Acceso(null, 5, "benjamin05", "Admin789", true);
                Acceso a6 = new Acceso(null, 6, "catalina06", "User321", true);
                Acceso a7 = new Acceso(null, 7, "vicente07", "Ingreso654", false);
                Acceso a8 = new Acceso(null, 8, "antonia08", "Ingreso987", true);
                accesoRepo.save(a1);
                accesoRepo.save(a2);
                accesoRepo.save(a3);
                accesoRepo.save(a4);
                accesoRepo.save(a5);
                accesoRepo.save(a6);
                accesoRepo.save(a7);
                accesoRepo.save(a8);
                System.out.println("Datos de acceso cargados correctamente");
            }
        };
    }
}
