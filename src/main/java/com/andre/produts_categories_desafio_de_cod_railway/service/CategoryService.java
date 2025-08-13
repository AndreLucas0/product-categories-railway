package com.andre.produts_categories_desafio_de_cod_railway.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andre.produts_categories_desafio_de_cod_railway.model.Category;
import com.andre.produts_categories_desafio_de_cod_railway.model.Product;
import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.CategoryResponseDTO;
import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.CreateCategoryDTO;
import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.UpdateCategoryDTO;
import com.andre.produts_categories_desafio_de_cod_railway.repository.CategoryRepository;
import com.andre.produts_categories_desafio_de_cod_railway.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public CategoryResponseDTO create(CreateCategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.name());

        if (dto.productIds() != null && !dto.productIds().isEmpty()) {
            Set<Product> products = new HashSet<>();
            for (UUID productId : dto.productIds()) {
                Product product = productRepository.findByIdWithCategories(productId)
                        .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
                products.add(product);
            }
            category.setProducts(products);
        }

        categoryRepository.save(category);
        return CategoryResponseDTO.fromEntity(category);
    }

    public List<CategoryResponseDTO> listAll() {
        return categoryRepository.findAllWithProducts().stream().map(CategoryResponseDTO::fromEntity).toList();
    }

    public CategoryResponseDTO update(UUID id, UpdateCategoryDTO dto) {
        Category category = categoryRepository.findByIdWithProducts(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));

        if (!dto.name().isBlank() && dto.name() != null) {
            category.setName(dto.name());
        } if (dto.productIds() != null) {
            category.getProducts().clear();
            if (!dto.productIds().isEmpty()) {
                Set<Product> products = new HashSet<>();
                for (UUID productId : dto.productIds()) {
                    Product product = productRepository.findByIdWithCategories(productId)
                            .orElseThrow(() -> new EntityNotFoundException("Product not found: " + productId));
                    products.add(product);
                }
                category.getProducts().addAll(products);
            }
        }

        categoryRepository.save(category);
        return CategoryResponseDTO.fromEntity(category);
    }

    public void delete(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found:" + id));
        categoryRepository.delete(category);
    }
}
