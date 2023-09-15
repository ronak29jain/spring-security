package com.springboot.springsecurity.exception;

import jakarta.persistence.EntityExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.InstanceNotFoundException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class ExceptionController {

    Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        logger.error(badCredentialsException.getMessage());
        return new ResponseEntity<String>(badCredentialsException.getMessage(), new HttpHeaders(), 401);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<String>("Exception is thrown somewhere: " + exception.getMessage(), new HttpHeaders(), 404);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handleEntityExistsException(EntityExistsException entityExistsException) {
        logger.error(entityExistsException.getMessage());
        return new ResponseEntity<String>(entityExistsException.getMessage(), new HttpHeaders(), 404);
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> handleInstanceNotFoundException(InstanceNotFoundException instanceNotFoundException) {
        logger.error(instanceNotFoundException.getMessage());
        return new ResponseEntity<String>(instanceNotFoundException.getMessage(), new HttpHeaders(), 404);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, Object> objectBody = new LinkedHashMap<>();
        objectBody.put("Current Timestamp", new Date());
//        objectBody.put("Status", httpStatus.value());
        objectBody.put("Status", 400);

        // Get all errors
        List<String> exceptionalErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        objectBody.put("Errors", exceptionalErrors);

        logger.error("Error in Javax Validation. message: " + exceptionalErrors);

        return new ResponseEntity<>(objectBody, HttpStatus.BAD_REQUEST);
    }
}
