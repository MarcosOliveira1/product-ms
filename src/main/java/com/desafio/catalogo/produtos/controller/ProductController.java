package com.desafio.catalogo.produtos.controller;

import com.desafio.catalogo.produtos.DTOs.ProductDTO;
import com.desafio.catalogo.produtos.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@Api(tags = "ProductController")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Criar produto", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto criado"),
            @ApiResponse(code = 400, message = "Erro de validação"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO) {
        productDTO = productService.createProduct(productDTO);

        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Listar produtos", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de produtos"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll() {
        return productService.findAll();
    }

    @ApiOperation(value = "Listar produto localizado na base de dados", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listar de produto"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            ProductDTO dto = productService.findById(id);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Atualizar produto", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto atualizado"),
            @ApiResponse(code = 400, message = "Erro de validação"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO dto = productService.updateProduct(id, productDTO);
        if (dto != null){
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Listar produtos localizado na base de dados filtrado", response = ProductDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de produtos"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/search", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> search(@RequestParam(value = "q", defaultValue = "") String q,
                                    @RequestParam(value = "max_price", defaultValue = "") Double maxPrice,
                                    @RequestParam(value = "min_price", defaultValue = "") Double minPrice) {
        return productService.search(q, minPrice, maxPrice);
    }

    @ApiOperation(value = "Deleta produtos localizado na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleta de produto"),
            @ApiResponse(code = 404, message = "Produto não encontrado"),
    })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return productService.delete(id);
    }
}
