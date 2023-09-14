package ua.vart.portfolio.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ua.vart.portfolio.exception.ClientNotFoundException;
import ua.vart.portfolio.exception.CodeAlreadyUsedException;
import ua.vart.portfolio.exception.CodeNotFoundException;
import ua.vart.portfolio.exception.FeedbackNotFoundException;

@RestController
@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler({
            ClientNotFoundException.class,
            CodeAlreadyUsedException.class,
            CodeNotFoundException.class,
            FeedbackNotFoundException.class
    })
    public ResponseEntity<?> customErrorHandler(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
