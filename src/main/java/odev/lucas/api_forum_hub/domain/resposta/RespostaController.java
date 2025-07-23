package odev.lucas.api_forum_hub.domain.resposta;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    private final RespostaService respostaService;

    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @PostMapping
    public ResponseEntity<RespostaResponseDTO> cadastrar(@Valid @RequestBody RespostaCadastroDTO cadastroDTO, UriComponentsBuilder uriBuilder) {
        Resposta resposta = respostaService.cadastrar(cadastroDTO);

        URI uri = uriBuilder.path("/{id}").build(resposta.getId());

        return ResponseEntity.created(uri).body(new RespostaResponseDTO(resposta));

    }

}
