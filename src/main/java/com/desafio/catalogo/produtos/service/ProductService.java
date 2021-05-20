package com.desafio.catalogo.produtos.service;

import com.desafio.catalogo.produtos.DTOs.ProductDTO;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    ResponseEntity<?> updateProduct(Long id, ProductDTO productDTO);

    ProductDTO findById(Long id);

    ResponseEntity<?> findAll();

    ResponseEntity<?> search(String q, Double minPrice, Double maxPrice);

    ResponseEntity<?> delete(Long id);
}
