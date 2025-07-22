package odev.lucas.api_forum_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoAtualizacaoDto(
        String titulo,
        String mensagem,
        String nomeCurso
) {
}
