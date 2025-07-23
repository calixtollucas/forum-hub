package odev.lucas.api_forum_hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Boolean existsByEmail(@NotBlank String email);
}
