package odev.lucas.api_forum_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoCadastroDto(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Long autor,
        @NotBlank
        String nomeCurso
) {
}
