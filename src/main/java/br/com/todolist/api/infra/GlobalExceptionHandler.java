package br.com.todolist.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequestException(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(DataValidationException::new));
    }

    private record DataValidationException(String field, String message){

        public DataValidationException(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }

    }

}
