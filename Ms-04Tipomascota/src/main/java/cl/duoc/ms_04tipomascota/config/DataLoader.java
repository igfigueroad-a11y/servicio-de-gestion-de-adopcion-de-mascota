package cl.duoc.ms_04tipomascota.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.ms_04tipomascota.model.TipoMascota;
import cl.duoc.ms_04tipomascota.repository.TipoMascotaRepository;
@Configuration
public class DataLoader {
 @Bean
    CommandLineRunner initData(TipoMascotaRepository tipoRepo) {
        return args -> {
            if (tipoRepo.count() == 0) {
                TipoMascota t1 = new TipoMascota(null, "Perro", "Mascotas caninas de distintos tamanos y razas");
                TipoMascota t2 = new TipoMascota(null, "Gato", "Mascotas felinas ideales para hogar o departamento");
                TipoMascota t3 = new TipoMascota(null, "Conejo", "Mamiferos pequenos y tranquilos para adopcion");
                TipoMascota t4 = new TipoMascota(null, "Ave", "Mascotas con plumas como canarios y periquitos");
                TipoMascota t5 = new TipoMascota(null, "Huron", "Mascota domestica exotica, curiosa y activa");
                TipoMascota t6 = new TipoMascota(null, "Hamster", "Roedor pequeno, facil de cuidar en espacios reducidos");
                TipoMascota t7 = new TipoMascota(null, "Tortuga", "Reptil domestico de caracter tranquilo");
                TipoMascota t8 = new TipoMascota(null, "Cobayo", "Mamifero pequeno sociable y amigable");
                TipoMascota t9 = new TipoMascota(null, "Pez", "Mascota acuatica ornamental de facil mantenimiento");
                TipoMascota t10 = new TipoMascota(null, "Loro", "Ave inteligente y muy interactiva");
                tipoRepo.save(t1);
                tipoRepo.save(t2);
                tipoRepo.save(t3);
                tipoRepo.save(t4);
                tipoRepo.save(t5);
                tipoRepo.save(t6);
                tipoRepo.save(t7);
                tipoRepo.save(t8);
                tipoRepo.save(t9);
                tipoRepo.save(t10);
                System.out.println("Datos de tipo mascota cargados correctamente");
            }
        };
    }
}
