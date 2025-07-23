package odev.lucas.api_forum_hub.domain.usuario;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioCadastroDTO cadastroDTO, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioService.cadastrar(cadastroDTO);

        URI uri = uriBuilder.path("/{id}").build(usuario.getId());
        UsuarioResponseDTO response = new UsuarioResponseDTO(usuario);

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        Page<UsuarioResponseDTO> pages = usuarioService.listarTodosPageavel(pageable).map(
                UsuarioResponseDTO::new
        );
        return ResponseEntity.ok(pages);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> detalhar(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.findById(id);

        return ResponseEntity.ok(new UsuarioResponseDTO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable("id") Long id,
                                                        @RequestBody UsuarioAtualizacaoDTO atualizacaoDTO) {

    }

}
