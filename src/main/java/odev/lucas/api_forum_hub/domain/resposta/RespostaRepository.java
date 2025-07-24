package odev.lucas.api_forum_hub.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import odev.lucas.api_forum_hub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    boolean existsByMensagem(String mensagem);

    Page<Resposta> findByTopicoAndAtivoTrue(Topico topico, Pageable pageable);

    boolean existsByMensagemAndAtivoTrue(String mensagem);

    Page<Resposta> findByAtivoTrue(Pageable pageable);

    Optional<Resposta> findByIdAndAtivoTrue(Long id);
}
