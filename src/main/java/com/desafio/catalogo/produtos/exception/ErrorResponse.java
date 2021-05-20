package com.desafio.catalogo.produtos.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@Data
public class ErrorResponse {

    private Integer statusCode;
    private String message;

}
