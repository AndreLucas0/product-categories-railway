package com.andre.produts_categories_desafio_de_cod_railway.model.DTO;

import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryDTO(@NotBlank(message = "Name is required.") String name, Set<UUID> productIds) {
    
}
