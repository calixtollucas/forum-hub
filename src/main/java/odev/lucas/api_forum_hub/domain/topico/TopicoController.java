package odev.lucas.api_forum_hub.domain.topico;

import jakarta.validation.Valid;
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
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
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

}
