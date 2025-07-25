package odev.lucas.api_forum_hub.domain.topico;

import odev.lucas.api_forum_hub.domain.curso.Curso;
import odev.lucas.api_forum_hub.domain.curso.CursoService;
import odev.lucas.api_forum_hub.domain.usuario.Usuario;
import odev.lucas.api_forum_hub.domain.usuario.UsuarioService;
import odev.lucas.api_forum_hub.infra.exceptions.DomainException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

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
        boolean topicoExists = topicoRepository.existsByTituloAndMensagemAndAtivoTrue(cadastroDto.titulo(), cadastroDto.mensagem());

        if (topicoExists) {
            throw new DomainException("Já existe um tópico com este título e mensagem", HttpStatus.CONFLICT);
        }

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
                curso,
                true);

        topicoRepository.save(topico);
        return topico;
    }

    public Page<Topico> findAllPageable(Pageable pageable, String curso, Integer ano) {

        if (curso != null && ano != null) {
            Curso cursoEntity = cursoService.findByNome(curso);
            return topicoRepository.findByCursoAndAnoAndAtivoTrue(cursoEntity, ano, pageable);
        } else if (curso != null) {
            Curso cursoEntity = cursoService.findByNome(curso);
            return topicoRepository.findByCursoAndAtivoTrue(cursoEntity, pageable);
        } else if (ano != null){
            return topicoRepository.findByAnoAndAtivoTrue(ano, pageable);
        } else {
            return topicoRepository.findByAtivoTrue(pageable);
        }
    }

    public Topico findById(Long id) {
        return topicoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DomainException("Topico não existe", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Topico atualizar(TopicoAtualizacaoDto dto, Long id) {
        //procura topico pelo id
        Optional<Topico> topicoOptional = topicoRepository.findByIdAndAtivoTrue(id);
        if (topicoOptional.isEmpty()) {
            throw new DomainException("O topico não existe", HttpStatus.NOT_FOUND);
        }

        Curso curso = null;
        //procura o curso

        if (dto.nomeCurso() != null) {
            curso = cursoService.findByNome(dto.nomeCurso());
        }

        Topico topico = topicoOptional.get();
        topico.atualizar(dto, curso);

        return topico;
    }

    @Transactional
    public void desativar(Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findByIdAndAtivoTrue(id);
        Topico topico = topicoOptional.orElseThrow(() -> new DomainException("Topico não encontrado", HttpStatus.NOT_FOUND));
        topico.desativar();
    }
}
