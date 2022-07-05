package br.com.marcelpinotti.gerenciadordeingressos.exception.handler;

import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectExistsException;
import br.com.marcelpinotti.gerenciadordeingressos.exception.ObjectNotFoundException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception,
                                                        HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado",
                                    exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ObjectExistsException.class)
    public ResponseEntity<StandardError> objectExists(ObjectExistsException exception,
                                                      HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;

        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Já existe",
                exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<StandardError> badRequest(FeignException exception,
                                                      HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String message = "Possivelmente o CEP digitado é inválido, o mesmo só aceita números. Exemplos, 12345678 ou 12345-678";


        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado",
                message, request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }
}
