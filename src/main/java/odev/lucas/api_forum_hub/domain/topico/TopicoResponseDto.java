package odev.lucas.api_forum_hub.domain.topico;

import odev.lucas.api_forum_hub.domain.curso.Curso;

import java.time.LocalDate;

public record TopicoResponseDto(
        Long id,
        String titulo,
        String mensagem,
        LocalDate dataCriacao,
        Status status,
        String autor,
        Curso curso
) {
    public TopicoResponseDto(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso());
    }
}
