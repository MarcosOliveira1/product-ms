package com.desafio.catalogo.produtos.entity;

import com.desafio.catalogo.produtos.DTOs.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "product", schema = "API")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "price")
    @NotNull
    @Digits(integer = 20, fraction = 2)
    private Double price;

    public static Product create(ProductDTO productDTO){
        return new ModelMapper().map(productDTO, Product.class);
    }
}
