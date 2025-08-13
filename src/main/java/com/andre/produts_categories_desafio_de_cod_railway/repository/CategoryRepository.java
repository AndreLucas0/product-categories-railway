package com.andre.produts_categories_desafio_de_cod_railway.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.andre.produts_categories_desafio_de_cod_railway.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>{
    
    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.products")
    List<Category> findAllWithProducts();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.products WHERE c.id = :id")
    Optional<Category> findByIdWithProducts(@Param("id") UUID id);
}
