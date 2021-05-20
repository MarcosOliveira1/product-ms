package com.desafio.catalogo.produtos.repository;

import com.desafio.catalogo.produtos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where (p.name like %:q% or p.description like %:q%) and p.price >= :minPrice and p.price <= :maxPrice")
    List<Product> findAllFiltered(String q, Double minPrice, Double maxPrice);
}
