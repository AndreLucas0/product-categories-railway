package com.andre.produts_categories_desafio_de_cod_railway.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andre.produts_categories_desafio_de_cod_railway.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
    
}
