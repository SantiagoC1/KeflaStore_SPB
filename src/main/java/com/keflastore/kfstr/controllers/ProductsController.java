package com.keflastore.kfstr.controllers;

import com.keflastore.kfstr.entities.Product;
import com.keflastore.kfstr.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    //CREAR
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productsService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    //LEER TODOS
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productsService.readAll();
        return ResponseEntity.ok(products);
    }

    //LEER UNO
    @GetMapping("/{pid}")
    public ResponseEntity<Product> getProductById(@PathVariable Long pid) {
        Optional<Product> product = productsService.readOne(pid);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //ACTUALIZAR
    @PutMapping("/{pid}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long pid, @RequestBody Product product) {
        Optional<Product> productOptional= productsService.readOne(pid);
        if(productOptional.isPresent()) {
            Product productU = productOptional.get();
            productU.setName(product.getName());
            productU.setPrice(product.getPrice());
            productU.setStock(product.getStock());
            Product productUpdated = productsService.save(productU);
            return ResponseEntity.ok(productUpdated);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productsService.deleteOne(id);
    }
}
