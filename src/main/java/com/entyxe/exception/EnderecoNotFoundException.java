package com.entyxe.exception;

public class EnderecoNotFoundException extends RuntimeException{
    public EnderecoNotFoundException(Long id) {
        super("Endereço não encontrado. ID: "+ id);
    }
}
