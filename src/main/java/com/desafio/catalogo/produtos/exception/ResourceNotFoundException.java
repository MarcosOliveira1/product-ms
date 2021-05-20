package com.desafio.catalogo.produtos.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String exception) {
        super(exception);
    }

}
