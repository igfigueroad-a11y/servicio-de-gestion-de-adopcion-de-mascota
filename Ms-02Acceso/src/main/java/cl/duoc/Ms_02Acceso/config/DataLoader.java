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
            System.out.println("Iniciando carga de datos en acceso...");

            accesoRepo.save(new Acceso(null, 1, "sebastian01", "Pass123", true));
            accesoRepo.save(new Acceso(null, 2, "fernanda02", "Pass123", true));
            accesoRepo.save(new Acceso(null, 3, "tomas03", "Clave456", true));
            accesoRepo.save(new Acceso(null, 4, "daniela04", "Clave456", true));
            accesoRepo.save(new Acceso(null, 5, "benjamin05", "Admin789", true));
            accesoRepo.save(new Acceso(null, 6, "catalina06", "User321", true));
            accesoRepo.save(new Acceso(null, 7, "vicente07", "Ingreso654", false));
            accesoRepo.save(new Acceso(null, 8, "antonia08", "Ingreso987", true));

            System.out.println("Datos de acceso cargados correctamente");
        } else {
            System.out.println("La tabla acceso ya tiene datos, no se cargan registros.");
        }
    };
}
}