package odev.lucas.api_forum_hub.domain.topico;

import odev.lucas.api_forum_hub.domain.curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("SELECT t FROM Topico t WHERE t.curso = :curso AND YEAR(dataCriacao) = :ano")
    Page<Topico> findByCursoAndAno(Curso curso, Integer ano, Pageable pageable);

    Page<Topico> findByCurso(Curso curso, Pageable pageable);

    @Query("SELECT t FROM Topico t WHERE YEAR(dataCriacao) = :ano")
    Page<Topico> findByAno(Integer ano, Pageable pageable);
}
