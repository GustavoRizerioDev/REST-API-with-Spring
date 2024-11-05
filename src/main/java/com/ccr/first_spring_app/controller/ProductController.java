package com.ccr.first_spring_app.controller;

import com.ccr.first_spring_app.model.Product;
import com.ccr.first_spring_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        if (productService.findById(product.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID já existe. Produto não foi adicionado.");
        }

        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado com sucesso.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (productService.findById(id).isPresent()) {
            productService.delete(id);
            return ResponseEntity.ok("Produto deletado com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
    }
}
