package odev.lucas.api_forum_hub.domain.topico;

import odev.lucas.api_forum_hub.domain.curso.Curso;
import odev.lucas.api_forum_hub.domain.curso.CursoService;
import odev.lucas.api_forum_hub.domain.usuario.Usuario;
import odev.lucas.api_forum_hub.domain.usuario.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        //verifica se o topico existe pelo titulo


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

    public Page<Topico> findAllPageable(Pageable pageable, String curso, Integer ano) {

        if (curso != null && ano != null) {
            Curso cursoEntity = cursoService.findByNome(curso);
            return topicoRepository.findByCursoAndAno(cursoEntity, ano, pageable);
        } else if (curso != null) {
            Curso cursoEntity = cursoService.findByNome(curso);
            return topicoRepository.findByCurso(cursoEntity, pageable);
        } else if (ano != null){
            return topicoRepository.findByAno(ano, pageable);
        } else {
            return topicoRepository.findAll(pageable);
        }
    }
}
