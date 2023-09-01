package be.bstorm.technifutur_java23_demospringsecurity.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handlePrecondition(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
