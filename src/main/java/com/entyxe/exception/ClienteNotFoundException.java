package com.entyxe.exception;

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException(Long id) {
        super("Cliente não encontrado. ID: " + id);
    }
}
