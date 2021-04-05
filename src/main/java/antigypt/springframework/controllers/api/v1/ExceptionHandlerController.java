package antigypt.springframework.controllers.api.v1;

import antigypt.springframework.exceptions.DuplicateException;
import antigypt.springframework.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(Exception exception, WebRequest webRequest){
        String errorMessage = "The object doesnt exists";
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<Object> handleDuplicateException(Exception exception, WebRequest webRequest){
        String errorMessage = "the object already exists";
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
