package com.guidev.transacao_simplificada.controller;

import com.guidev.transacao_simplificada.infrastructure.exceptions.BadRequestException;
import com.guidev.transacao_simplificada.infrastructure.exceptions.UserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>("Erro: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handlerBadRequestException(BadRequestException e){
        return new ResponseEntity<>("Erro: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handlerUserNotFoundException(UserNotFound e){
        return new ResponseEntity<>("Erro: "+ e.getMessage(), HttpStatus.NOT_FOUND);
    }


}
