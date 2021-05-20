package com.desafio.catalogo.produtos.serviceImpl;

import com.desafio.catalogo.produtos.DTOs.ProductDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.yml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    Long idCriado = 0L;

    @Test
    @Order(1)
    public void deveCadastrarProduto() {
        final ProductDTO productDTO = new ProductDTO(null, "nome", "descricao", 50.0);

        final ProductDTO cadastro = productService.createProduct(productDTO);

        Assertions.assertNotNull(cadastro);
        Assertions.assertNotNull(cadastro.getId());
        this.idCriado = cadastro.getId();
    }

    @Test
    @Order(2)
    public void naoDeveCadastrarProdutoNameNull() {
        final ProductDTO productDTO = new ProductDTO(null, null, "descricao", 50.0);
        final ProductDTO cadastro = productService.createProduct(productDTO);
        Assertions.assertNull(cadastro);
    }

    @Test
    @Order(3)
    public void naoDeveCadastrarProdutoPriceMenorZero() {
        final ProductDTO productDTO = new ProductDTO(null, "nome", "descricao", -1.0);
        final ProductDTO cadastro = productService.createProduct(productDTO);
        Assertions.assertNull(cadastro);
    }

    @Test
    @Order(4)
    public void naoDeveCadastrarProdutoDescriptionNula() {
        final ProductDTO productDTO = new ProductDTO(null, "nome", null, -1.0);
        final ProductDTO cadastro = productService.createProduct(productDTO);
        Assertions.assertNull(cadastro);
    }

}
