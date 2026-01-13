package com.project.first_vaddin.controller;

import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class HttpGlobalErrorController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<?> handleWithEmailWhichIsAlreadyExist(UnexpectedTypeException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>("This email is exist in database" ,HttpStatus.BAD_REQUEST);
    }


}