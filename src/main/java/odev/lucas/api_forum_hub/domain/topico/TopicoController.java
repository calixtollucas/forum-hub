package odev.lucas.api_forum_hub.domain.topico;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<TopicoResponseDto> cadastrar(@RequestBody @Valid TopicoCadastroDto cadastroDto, UriComponentsBuilder uriBuilder) {
        Topico topicoCadastrado = topicoService.cadastrar(cadastroDto);

        URI uri = uriBuilder.path("/topicos/{id}").build(topicoCadastrado.getId());

        return ResponseEntity.created(uri).body(new TopicoResponseDto(topicoCadastrado));
    }

}
