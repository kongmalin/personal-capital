package com.personalcapital.montecarlosimulation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.invoke.MethodHandles;

@ControllerAdvice
public class ControllerAdvise {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> defaultErrorHandler(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorResult(HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResult> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorResult("Invalid request body.", "Invalid request body."), HttpStatus.BAD_REQUEST);
    }

}
