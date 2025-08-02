package com.address.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundExceptionHandlerMethod(ResourceNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionMethod(MethodArgumentNotValidException ex){
        ErrorResponse errorResponse = null;
        ObjectError errorMessage = ex.getBindingResult().getFieldError();
        List<String> errorMessageList = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            errorMessageList.add(error.getDefaultMessage());
        }

        if(errorMessageList.size() > 1){
            errorResponse = new ErrorResponse(errorMessageList.toString(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        else {
            errorResponse = new ErrorResponse(errorMessage.getDefaultMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
