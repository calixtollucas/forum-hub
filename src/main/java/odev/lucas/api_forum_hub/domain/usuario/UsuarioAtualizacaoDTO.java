package odev.lucas.api_forum_hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioAtualizacaoDTO(
        @NotBlank
        String nome
) {
}
