package odev.lucas.api_forum_hub.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ExceptionDto> domainException(DomainException ex) {
        return ResponseEntity.status(ex.getHttpStatus().value()).body(new ExceptionDto(ex.getHttpStatus(), ex.getMessage()));
    }
}
