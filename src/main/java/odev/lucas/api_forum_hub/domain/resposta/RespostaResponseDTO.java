package odev.lucas.api_forum_hub.domain.resposta;

import odev.lucas.api_forum_hub.domain.topico.TopicoResponseDto;
import odev.lucas.api_forum_hub.domain.usuario.UsuarioResponseDTO;

import java.time.LocalDate;

public record RespostaResponseDTO(
        Long id,
        String mensagem,
        TopicoResponseDto topico,
        LocalDate dataCriacao,
        UsuarioResponseDTO autor,
        String solucao
) {
}
