package estudo.com.api_tarefas.infra;

import estudo.com.api_tarefas.exception.InvalidCredentialsException;
import estudo.com.api_tarefas.exception.ResourceAlreadyExistsException;
import estudo.com.api_tarefas.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestError> handleResourceNotFoundException(ResourceNotFoundException ex){

        RestError restError = new RestError(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restError);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<RestError> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex){
        RestError restError = new RestError(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(restError);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<RestError> handleInvalidCredentialsException(InvalidCredentialsException ex){
        RestError restError = new RestError(
                HttpStatus.UNAUTHORIZED,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(restError);
    }
}
