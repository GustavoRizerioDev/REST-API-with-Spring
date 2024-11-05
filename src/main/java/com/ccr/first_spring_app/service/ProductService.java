package com.ccr.first_spring_app.service;

import com.ccr.first_spring_app.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public List<Product> findAll(){
        return products;
    }

    public Optional<Product> findById(Long id){
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    public Product save(Product product) {
        products.add(product);
        return product;
    }

    public void delete(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
}
