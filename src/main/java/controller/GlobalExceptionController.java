package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * Gestion centralisée des erreurs
 */
@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler
    public ResponseEntity<String> handleThatException(IOException e) {
        return new ResponseEntity("custom", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}


