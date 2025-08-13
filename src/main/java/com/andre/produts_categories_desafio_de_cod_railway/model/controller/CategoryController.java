package com.andre.produts_categories_desafio_de_cod_railway.model.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.CategoryResponseDTO;
import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.CreateCategoryDTO;
import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.UpdateCategoryDTO;
import com.andre.produts_categories_desafio_de_cod_railway.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody @Valid CreateCategoryDTO dto) {
        CategoryResponseDTO category = categoryService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.id())
                .toUri();
        return ResponseEntity.created(location).body(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> listAll() {
        return ResponseEntity.ok(categoryService.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable UUID id, @RequestBody UpdateCategoryDTO dto) {
        return ResponseEntity.ok(categoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
