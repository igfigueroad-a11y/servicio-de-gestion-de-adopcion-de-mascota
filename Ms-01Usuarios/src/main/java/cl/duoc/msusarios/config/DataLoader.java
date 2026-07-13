package cl.duoc.msusarios.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.duoc.msusarios.model.Usuario;
import cl.duoc.msusarios.repository.UsuarioRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(UsuarioRepository userRepo) {
        return args -> {
            if (userRepo.count() == 0) {
                Usuario u1 = new Usuario(null, "Sebastian Riquelme", "sebastian@gmail.com", "20111111-1", "991234567", "Av. Matta 120", "Santiago", LocalDate.of(2001, 3, 14), LocalDateTime.now(), new BigDecimal("780000"));
                Usuario u2 = new Usuario(null, "Fernanda Olivares", "fernanda@gmail.com", "20222222-2", "992345678", "Los Aromos 455", "Maipu", LocalDate.of(2000, 7, 21), LocalDateTime.now(), new BigDecimal("650000"));
                Usuario u3 = new Usuario(null, "Tomas Aravena", "tomas@gmail.com", "20333333-3", "993456789", "Pasaje El Sol 89", "Puente Alto", LocalDate.of(1998, 11, 2), LocalDateTime.now(), new BigDecimal("820000"));
                Usuario u4 = new Usuario(null, "Daniela Saez", "daniela@gmail.com", "20444444-4", "994567891", "Av. Vicuna Mackenna 555", "La Florida", LocalDate.of(2002, 1, 17), LocalDateTime.now(), new BigDecimal("540000"));
                Usuario u5 = new Usuario(null, "Benjamin Morales", "benjamin@gmail.com", "20555555-5", "995678912", "Las Camelias 300", "Providencia", LocalDate.of(1999, 9, 9), LocalDateTime.now(), new BigDecimal("910000"));
                Usuario u6 = new Usuario(null, "Catalina Fuentes", "catalina@gmail.com", "20666666-6", "996789123", "San Diego 780", "Santiago", LocalDate.of(2003, 4, 30), LocalDateTime.now(), new BigDecimal("610000"));
                Usuario u7 = new Usuario(null, "Vicente Paredes", "vicente@gmail.com", "20777777-7", "997891234", "Los Naranjos 612", "Quilicura", LocalDate.of(1997, 12, 25), LocalDateTime.now(), new BigDecimal("970000"));
                Usuario u8 = new Usuario(null, "Antonia Carrasco", "antonia@gmail.com", "20888888-8", "998912345", "Av. Tobalaba 1400", "Nunoa", LocalDate.of(2001, 6, 5), LocalDateTime.now(), new BigDecimal("700000"));

                userRepo.save(u1);
                userRepo.save(u2);
                userRepo.save(u3);
                userRepo.save(u4);
                userRepo.save(u5);
                userRepo.save(u6);
                userRepo.save(u7);
                userRepo.save(u8);

                System.out.println("Datos de usuarios cargados correctamente");
            }
        };
    }
}