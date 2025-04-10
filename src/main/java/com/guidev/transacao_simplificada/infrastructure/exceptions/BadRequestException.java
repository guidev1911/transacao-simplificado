package com.guidev.transacao_simplificada.infrastructure.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }
}