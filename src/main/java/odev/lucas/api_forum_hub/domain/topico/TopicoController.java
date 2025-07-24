package odev.lucas.api_forum_hub.domain.topico;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import odev.lucas.api_forum_hub.domain.resposta.RespostaResponseDTO;
import odev.lucas.api_forum_hub.domain.resposta.RespostaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private final TopicoService topicoService;
    private final RespostaService respostaService;

    public TopicoController(TopicoService topicoService, RespostaService respostaService) {
        this.topicoService = topicoService;
        this.respostaService = respostaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoResponseDto> cadastrar(@RequestBody @Valid TopicoCadastroDto cadastroDto, UriComponentsBuilder uriBuilder) {
        Topico topicoCadastrado = topicoService.cadastrar(cadastroDto);

        URI uri = uriBuilder.path("/topicos/{id}").build(topicoCadastrado.getId());

        return ResponseEntity.created(uri).body(new TopicoResponseDto(topicoCadastrado));
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponseDto>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer ano) {
        Page<TopicoResponseDto> page = topicoService.findAllPageable(pageable, curso, ano).map(TopicoResponseDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDto> detalhar(@PathVariable("id") Long id) {
        Topico topico = topicoService.findById(id);
        return ResponseEntity.ok(new TopicoResponseDto(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDto> atualizar(@RequestBody TopicoAtualizacaoDto dto, @PathVariable("id") Long id) {
        Topico topico = topicoService.atualizar(dto, id);
        return ResponseEntity.ok(new TopicoResponseDto(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        topicoService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/respostas")
    public ResponseEntity<Page<RespostaResponseDTO>> listarRespostasPorTopico(@PathVariable("id") Long id,
                                                                              Pageable pageable) {
        Page<RespostaResponseDTO> respostas = respostaService.buscarPorTopicoPageable(id, pageable).map(
                RespostaResponseDTO::new
        );

        return ResponseEntity.ok(respostas);
    }

}
