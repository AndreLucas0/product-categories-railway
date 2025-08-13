package com.andre.produts_categories_desafio_de_cod_railway.model.DTO;

import java.util.List;
import java.util.UUID;

import com.andre.produts_categories_desafio_de_cod_railway.model.Product;

public record ProductResponseDTO(UUID id, String name, String description, Double price, List<String> categories) {
    public static ProductResponseDTO fromEntity(Product product) {
        return new ProductResponseDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(),
                product.getCategories().stream().map(c -> c.getName()).toList());
    }
}
