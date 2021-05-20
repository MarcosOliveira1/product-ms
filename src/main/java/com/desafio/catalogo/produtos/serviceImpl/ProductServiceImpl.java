package com.desafio.catalogo.produtos.serviceImpl;

import com.desafio.catalogo.produtos.DTOs.ProductDTO;
import com.desafio.catalogo.produtos.entity.Product;
import com.desafio.catalogo.produtos.repository.ProductRepository;
import com.desafio.catalogo.produtos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        productDTO = ProductDTO.create(productRepository.save(Product.create(productDTO)));
        return productDTO;
    }

    @Override
    public ResponseEntity<?> updateProduct(Long id, ProductDTO productDTO) {
        final Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            optionalProduct.ifPresent(product -> productDTO.setId(product.getId()));
            return ResponseEntity.ok(productRepository.save(Product.create(productDTO)));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductDTO.create(product);
    }

    @Override
    public ResponseEntity<?> findAll() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products.stream().map(this::convertToProductDTO));
    }

    @Override
    public ResponseEntity<?> search(String q, Double minPrice, Double maxPrice) {
        List<Product> products = productRepository.findAllFiltered(q, minPrice, maxPrice);
        List<ProductDTO> retorno = new ArrayList<>();
        products.forEach(product -> retorno.add(convertToProductDTO(product)));
        return ResponseEntity.ok(retorno);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            productRepository.delete(product.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ProductDTO convertToProductDTO(Product product) {
        return ProductDTO.create(product);
    }
}
