package odev.lucas.api_forum_hub.domain.topico;

import jakarta.validation.Valid;
import odev.lucas.api_forum_hub.domain.curso.Curso;
import odev.lucas.api_forum_hub.domain.curso.CursoService;
import odev.lucas.api_forum_hub.domain.usuario.Usuario;
import odev.lucas.api_forum_hub.domain.usuario.UsuarioService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final CursoService cursoService;
    private final UsuarioService usuarioService;

    public TopicoService(TopicoRepository topicoRepository,
                         CursoService cursoService,
                         UsuarioService usuarioService) {
        this.topicoRepository = topicoRepository;
        this.cursoService = cursoService;
        this.usuarioService = usuarioService;
    }

    public Topico cadastrar(TopicoCadastroDto cadastroDto) {
        //verifica se o curso existe
        Curso curso = cursoService.findByNome(cadastroDto.nomeCurso());
        //verifica se o autor existe
        Usuario autor = usuarioService.findById(cadastroDto.autor());
        //monta para salvar
        Topico topico = new Topico(
                null,
                cadastroDto.titulo(),
                cadastroDto.mensagem(),
                LocalDate.now(),
                Status.ABERTO,
                autor,
                curso);

        topicoRepository.save(topico);
        return topico;
    }
}
