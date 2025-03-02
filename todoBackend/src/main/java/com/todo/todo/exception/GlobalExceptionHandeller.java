package com.todo.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandeller {

    @ExceptionHandler(ToDoNotFoundException.class)
    public ResponseEntity<ErrorRresponse> handleToDoNotFoundException(ToDoNotFoundException ex, WebRequest request) {
        ErrorRresponse errorRresponse = new ErrorRresponse(
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false));

        return new ResponseEntity<>(errorRresponse, HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRresponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorRresponse errorRresponse = new ErrorRresponse(
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorRresponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
