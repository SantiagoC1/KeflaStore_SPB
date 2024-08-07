package com.keflastore.kfstr.services;


import com.keflastore.kfstr.entities.Product;
import com.keflastore.kfstr.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired private ProductsRepository pRepository;

    public Product create (Product product){
        return pRepository.save(product);
    }

    public List<Product> readAll(){
        return pRepository.findAll();
    }

    public Optional<Product> readOne(Long id){
        return pRepository.findById(id);
    }

    public Optional<Product> updateProduct(Long pid, Product newProductData) {
        Optional<Product> existingProduct = pRepository.findById(pid);
        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();

            if (newProductData.getName() != null) {
                productToUpdate.setName(newProductData.getName());
            }
            if (newProductData.getCategory() != null) {
                productToUpdate.setCategory(newProductData.getCategory());
            }
            if (newProductData.getPrice() != null) {
                productToUpdate.setPrice(newProductData.getPrice());
            }
            if (newProductData.getStock() != null) {
                productToUpdate.setStock(newProductData.getStock());
            }

            pRepository.save(productToUpdate);
            return Optional.of(productToUpdate);
        }
        return Optional.empty();
    }

    public Boolean deleteOne(Long id){
        Optional<Product> productOptional = readOne(id);
        if (productOptional.isPresent()) {
            pRepository.delete(productOptional.get());
            return true;
        } else {
            return false;
        }
    }

}
