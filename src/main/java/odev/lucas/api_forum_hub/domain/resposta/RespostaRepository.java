package odev.lucas.api_forum_hub.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    boolean existsByMensagem(String mensagem);
}
