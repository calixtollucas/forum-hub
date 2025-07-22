package odev.lucas.api_forum_hub.infra.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionDto(
        Integer statusCode,
        String mensagem
) {
    public ExceptionDto(HttpStatus status, String mensagem) {
        this(status.value(), mensagem);
    }
}
