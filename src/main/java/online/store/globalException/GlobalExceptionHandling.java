package online.store.globalException;

import online.store.exceptions.CreditCardValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(CreditCardValidationException.class)
    public ResponseEntity<String> creditCardValidationFailed(CreditCardValidationException e){
        System.out.println(String.format("Request to /checkout path threw an exception %s",e.getMessage()));
        return new ResponseEntity<String>("Credit card is invalid ,please use another form of payment", HttpStatus.BAD_REQUEST);
    }
}
