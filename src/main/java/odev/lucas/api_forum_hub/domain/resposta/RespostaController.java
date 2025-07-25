package odev.lucas.api_forum_hub.domain.resposta;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respostas")
@SecurityRequirement(name = "bearer-key")
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

    @GetMapping
    public ResponseEntity<Page<RespostaResponseDTO>> listarTodas(Pageable pageable) {
        Page<RespostaResponseDTO> respostas = respostaService.buscarTodas(pageable).map(RespostaResponseDTO::new);

        return ResponseEntity.ok(respostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaResponseDTO> detalhar(@PathVariable("id") Long id) {
        Resposta resposta = respostaService.buscarPorId(id);
        return ResponseEntity.ok(new RespostaResponseDTO(resposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaResponseDTO> atualizar(@RequestBody RespostaAtualizacaoDTO dto, @PathVariable("id") Long id) {
        Resposta resposta = respostaService.atualizar(dto, id);
        return ResponseEntity.ok(new RespostaResponseDTO(resposta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        respostaService.desativar(id);
        return ResponseEntity.noContent().build();
    }

}
