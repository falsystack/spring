package jp.falsystack.restful.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@Slf4j
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundExceptionHandler(UserNotFoundException e, WebRequest request) {
        log.info("UserNotFoundException", e);
        ExceptionResponse exceptionResponse =
                ExceptionResponse.of(LocalDate.now(), e.getMessage(), request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {

        ExceptionResponse exceptionResponse =
                ExceptionResponse.of(LocalDate.now(), e.getMessage(), request.getDescription(false));

        return ResponseEntity.internalServerError().body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex ,WebRequest request) {
        ExceptionResponse exceptionResponse =
                ExceptionResponse.of(LocalDate.now(), ex.getFieldErrors().get(0).getDefaultMessage(), request.getDescription(false));

        return ResponseEntity.badRequest().body(exceptionResponse);
    }
}
