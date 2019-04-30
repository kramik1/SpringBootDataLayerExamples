package com.test.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class AppExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity handleConstraintViolation(ConstraintViolationException ex) {
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity("Error ", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataAccessException.class, IncorrectResultSizeDataAccessException.class})
    public ResponseEntity handleJdbcErrors(DataAccessException ex) {
        log.warn(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceException("record not found"));
    }
}
