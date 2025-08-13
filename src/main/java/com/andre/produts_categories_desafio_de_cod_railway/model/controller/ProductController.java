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

import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.CreateProductDTO;
import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.ProductResponseDTO;
import com.andre.produts_categories_desafio_de_cod_railway.model.DTO.UpdateProductDTO;
import com.andre.produts_categories_desafio_de_cod_railway.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@RequestBody @Valid CreateProductDTO dto) {
        ProductResponseDTO product = productService.create(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.id())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> listAll() {
        return ResponseEntity.ok(productService.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable UUID id, @RequestBody UpdateProductDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
