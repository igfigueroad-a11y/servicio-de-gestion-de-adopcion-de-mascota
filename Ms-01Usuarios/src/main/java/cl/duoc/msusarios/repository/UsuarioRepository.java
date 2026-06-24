package cl.duoc.msusarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.msusarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>  {
Optional<Usuario> findByCorreoElectronico(String correo);
  boolean existsByCorreoElectronico(String correoElectronico);
}
