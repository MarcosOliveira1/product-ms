package com.desafio.catalogo.produtos.DTOs;

import com.desafio.catalogo.produtos.entity.Product;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Product")
public class ProductDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    @Digits(integer = 20, fraction = 2)
    private Double price;

    public static ProductDTO create(Product product){
        return new ModelMapper().map(product, ProductDTO.class);
    }
}
