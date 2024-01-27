package com.amadeus.flightsearchapi.handler;

import com.amadeus.flightsearchapi.domain.response.OperationResponse;
import com.amadeus.flightsearchapi.handler.exceptions.GeneralException;
import com.amadeus.flightsearchapi.handler.exceptions.UserNameAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<OperationResponse> exception(GeneralException exception) {
        return new ResponseEntity<>(new OperationResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<Map<String, List<String>>> handleUserNameAlreadyExistsException(UserNameAlreadyExistsException ex) {
        return new ResponseEntity<>(getErrorsMap(Collections.singletonList(ex.getMessage())), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
