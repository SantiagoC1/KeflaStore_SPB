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

    public Product save (Product product){
        return pRepository.save(product);
    }

    public List<Product> readAll(){
        return pRepository.findAll();
    }


    public Optional<Product> readOne(Long id){
        return pRepository.findById(id);
    }

    public void deleteOne(Long id){
        pRepository.deleteById(id);
    }

}
