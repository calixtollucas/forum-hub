package odev.lucas.api_forum_hub.domain.perfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long> {
    public Optional<PerfilEntity> findByNome(Perfil perfil);
}
