package com.andre.produts_categories_desafio_de_cod_railway.model.DTO;

import java.util.List;
import java.util.UUID;

import com.andre.produts_categories_desafio_de_cod_railway.model.Category;

public record CategoryResponseDTO(UUID id, String name, List<String> products) {
    public static CategoryResponseDTO fromEntity(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName(),
                category.getProducts().stream().map(p -> p.getName()).toList());
    }
}
