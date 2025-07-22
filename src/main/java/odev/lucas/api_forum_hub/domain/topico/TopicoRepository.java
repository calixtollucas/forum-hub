package odev.lucas.api_forum_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import odev.lucas.api_forum_hub.domain.curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND YEAR(dataCriacao) = :ano AND ativo = true")
    Page<Topico> findByCursoAndAnoAndAtivoTrue(Curso curso, Integer ano, Pageable pageable);

    Page<Topico> findByCursoAndAtivoTrue(Curso curso, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE YEAR(dataCriacao) = :ano AND ativo = true")
    Page<Topico> findByAnoAndAtivoTrue(Integer ano, Pageable pageable);

    boolean existsByTituloAndMensagemAndAtivoTrue(String titulo, String mensagem);

    Optional<Topico> findByIdAndAtivoTrue(Long id);

    Page<Topico> findByAtivoTrue(Pageable pageable);
}
