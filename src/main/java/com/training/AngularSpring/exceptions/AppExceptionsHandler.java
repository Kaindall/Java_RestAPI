package com.training.AngularSpring.exceptions;

import com.training.AngularSpring.model.response.ErrorModel;
import org.apache.coyote.Response;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionsHandler {
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleGenericException (Exception exception, WebRequest request) {
        ErrorModel responseValue = new ErrorModel(new Date());
        String localizedMessage = exception.getLocalizedMessage();

        if (responseValue.getErrorDetails() == null) {
            if (localizedMessage.contains("problem:")) {
                String errorKeyText = "problem: ";
                responseValue.setErrorDetails(Arrays.asList(localizedMessage
                        .substring(localizedMessage.lastIndexOf(errorKeyText))
                        .replace(errorKeyText, "")));

            } else if (localizedMessage.contains("JSON parse error:")) {
                String errorKeyText = "JSON parse error:";
                responseValue.setErrorDetails(Arrays.asList(localizedMessage
                        .substring(localizedMessage.lastIndexOf(errorKeyText))
                        .replace(errorKeyText, "")));
            }
        }

        return new ResponseEntity<>(responseValue, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<?> handleHttpException (HttpMessageNotReadableException exception, WebRequest request) {
        ErrorModel responseValue = new ErrorModel(new Date());
        String localizedMessage = exception.getLocalizedMessage();

        if (responseValue.getErrorDetails() == null) {
            if (localizedMessage.contains("problem:")) {
                String errorKeyText = "problem: ";
                responseValue.setErrorDetails(Arrays.asList(localizedMessage
                        .substring(localizedMessage.lastIndexOf(errorKeyText))
                        .replace(errorKeyText, "")));

            } else if (localizedMessage.contains("JSON parse error:")) {
                String errorKeyText = "JSON parse error:";
                responseValue.setErrorDetails(Arrays.asList(localizedMessage
                        .substring(localizedMessage.lastIndexOf(errorKeyText))
                        .replace(errorKeyText, "")));
            }
        }

        return new ResponseEntity<>(responseValue, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                               WebRequest request) {
        List<String> allErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldWrong -> fieldWrong.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorModel error = new ErrorModel(new Date(), allErrors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
