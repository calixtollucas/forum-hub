package odev.lucas.api_forum_hub.domain.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @Email
        String email,
        @NotBlank
        String senha
) {
}
