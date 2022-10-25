package recipes.presentationlayer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import org.springframework.web.context.request.WebRequest;
import recipes.businesslayer.AccessForbiddenException;
import recipes.businesslayer.ErrorMessage;
import recipes.businesslayer.NotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({ValidationException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorMessage> exceptionHandler(Exception e, WebRequest request) {
        ErrorMessage body = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessForbiddenException.class})
    public ResponseEntity<ErrorMessage> exceptionHandler(AccessForbiddenException e, WebRequest request) {
        ErrorMessage body = new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now(),
                "Access is forbidden. You have rights to change only your recipes.",
                request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorMessage> exceptionHandler(NotFoundException e, WebRequest request) {
        ErrorMessage body = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                "The recipe was not found. Please try again.",
                request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
