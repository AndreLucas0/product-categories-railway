package com.andre.produts_categories_desafio_de_cod_railway.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andre.produts_categories_desafio_de_cod_railway.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
    
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.categories")
    List<Product> findAllWithCategories();

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.categories WHERE p.id = :id")
    Optional<Product> findByIdWithCategories(@Param("id") UUID id);
}
