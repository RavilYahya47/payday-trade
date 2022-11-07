package com.ravilyahya.paydaytrade.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BalanceIsNotEnoughException.class)
    public ResponseEntity<Object> handleExceptions( BalanceIsNotEnoughException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("You don't have enough balance to place this operation");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<Object> handleExceptions( UserFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.FOUND);
        return entity;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleExceptions( UserNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(OrderTargetPriceDoesntMatchException.class)
    public ResponseEntity<Object> handleExceptions( OrderTargetPriceDoesntMatchException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Target price criteria doesn't match with stock price!\n"+
                "Your order added to the order execution pool!");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getFieldError().getDefaultMessage());
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        return entity;
    }







}