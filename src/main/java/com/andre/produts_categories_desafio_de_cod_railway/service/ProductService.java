package com.andre.produts_categories_desafio_de_cod_railway.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.produts_categories_desafio_de_cod_railway.model.Category;
import com.andre.produts_categories_desafio_de_cod_railway.model.Product;
import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.CreateProductDTO;
import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.UpdateProductDTO;
import com.andre.produts_categories_desafio_de_cod_railway.repository.CategoryRepository;
import com.andre.produts_categories_desafio_de_cod_railway.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public Product create(CreateProductDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());

        if (dto.categoryIds() != null && !dto.categoryIds().isEmpty()) {
            Set<Category> categories = new HashSet<>();
            for (UUID categoryId : dto.categoryIds()) {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found: " + categoryId));
                categories.add(category);
            }
            product.setCategories(categories);
        }

        productRepository.save(product);
        return product;
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product update(UUID id, UpdateProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));

        if (dto.name() != null || !dto.name().isBlank()) {
            product.setName(dto.name());
        }
        if (dto.price() != null) {
            product.setPrice(dto.price());
        }
        if (dto.description() != null || !dto.description().isBlank()) {
            product.setDescription(dto.description());
        }

        if (dto.categoryIds() != null) {
            product.getCategories().clear();
            if (!dto.categoryIds().isEmpty()) {
                Set<Category> categories = new HashSet<>();
                for (UUID categoryId : dto.categoryIds()) {
                    Category category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new EntityNotFoundException("Category not found: " + categoryId));
                    categories.add(category);
                }
                product.getCategories().addAll(categories);
            }
        }

        productRepository.save(product);
        return product;
    }

    public void delete(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
        productRepository.delete(product);
    }
}
