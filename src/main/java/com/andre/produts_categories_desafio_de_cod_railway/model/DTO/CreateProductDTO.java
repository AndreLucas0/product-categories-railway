package com.andre.produts_categories_desafio_de_cod_railway.model.DTO;

import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductDTO(@NotBlank(message = "Name is required.") String name, String description,
        @NotNull(message = "Price is required.") Double price, Set<UUID> categoryIds) {

}
