package odev.lucas.api_forum_hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Boolean existsByEmail(@NotBlank String email);

    Page<Usuario> findByAtivoTrue(Pageable pageable);

    UserDetails findByEmail(String username);
}
