package odev.lucas.api_forum_hub.infra.exceptions;

import org.springframework.http.HttpStatus;

public class DomainException extends RuntimeException {
    private final HttpStatus httpStatus;

    public DomainException(String mensagem, HttpStatus httpStatus) {
        super(mensagem);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
