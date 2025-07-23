package odev.lucas.api_forum_hub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioCadastroDTO(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String senha
) {
}
