package com.andre.produts_categories_desafio_de_cod_railway.model.DTO;

import java.util.Set;
import java.util.UUID;

public record UpdateCategoryDTO(String name, Set<UUID> productIds) {
    
}
