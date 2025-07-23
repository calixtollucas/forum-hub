package odev.lucas.api_forum_hub.domain.usuario;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
