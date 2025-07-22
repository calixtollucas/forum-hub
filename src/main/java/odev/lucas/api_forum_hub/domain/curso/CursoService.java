package odev.lucas.api_forum_hub.domain.curso;

import odev.lucas.api_forum_hub.infra.exceptions.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }
    public Curso findByNome(String nome) {
        return cursoRepository.findByNomeAndAtivoTrue(nome)
                .orElseThrow(
                        () -> {throw new DomainException("Curso não encontrado", HttpStatus.NOT_FOUND);}
        );
    }
}
