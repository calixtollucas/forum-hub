package odev.lucas.api_forum_hub.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespostaCadastroDTO(
        @NotBlank
        String mensagem,
        @NotNull
        Long topico,
        @NotNull
        Long autor,
        @NotBlank
        String solucao
) {
}
