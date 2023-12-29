package com.training.AngularSpring.exceptions;

import com.training.AngularSpring.model.responses.ErrorModel;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionsHandler {
    @ExceptionHandler({ HttpMessageNotReadableException.class, Exception.class })
    public ResponseEntity<?> handleGenericExceptions(Exception exception) {
        ErrorModel responseValue = new ErrorModel(new Date());
        String localizedMessage = exception.getLocalizedMessage();

        if (responseValue.getErrorDetails() == null) {
            if (localizedMessage.contains("problem:")) {
                String errorKeyText = "problem: ";
                responseValue.setErrorDetails(List.of(localizedMessage
                        .substring(localizedMessage.lastIndexOf(errorKeyText))
                        .replace(errorKeyText, "")));

            } else if (localizedMessage.contains("JSON parse error:")) {
                String errorKeyText = "JSON parse error:";
                responseValue.setErrorDetails(List.of(localizedMessage
                        .substring(localizedMessage.lastIndexOf(errorKeyText))
                        .replace(errorKeyText, "")));
            }
        }
        if (exception instanceof HttpMessageNotReadableException) {
            return new ResponseEntity<>(responseValue, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        if (responseValue.getErrorDetails() == null) responseValue.setErrorDetails(List.of(exception.getMessage()));

        //substituir por registro em log
        System.out.println(responseValue);
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodsValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> allErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorModel error = new ErrorModel(new Date(), allErrors, exception.getClass().getName());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ EmailRegisteredException.class })
    public ResponseEntity<Object> handleCreateUserExceptions(EmailRegisteredException exception) {
        ErrorModel error = new ErrorModel(new Date(), List.of(exception.getLocalizedMessage()),
                exception.getClass().getName());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<Object> handleFindUserExceptions(UserNotFoundException exception) {
        ErrorModel error = new ErrorModel(new Date(), List.of(exception.getLocalizedMessage()),
                exception.getClass().getName());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
