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
    public RespostaResponseDTO(Resposta resposta) {
        this(resposta.getId(),
                resposta.getMensagem(),
                new TopicoResponseDto(resposta.getTopico()),
                resposta.getDataCriacao(),
                new UsuarioResponseDTO(resposta.getAutor()),
                resposta.getSolucao());
    }
}
