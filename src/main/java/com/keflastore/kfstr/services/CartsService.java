package com.keflastore.kfstr.services;

import com.keflastore.kfstr.entities.Cart;
import com.keflastore.kfstr.entities.Product;
import com.keflastore.kfstr.repositories.CartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsService {
    @Autowired
    private CartsRepository cartsRepository;

    public Cart save(Cart cart) {
        return cartsRepository.save(cart);
    }

    public List<Cart>findAll(){
        return cartsRepository.findAll();
    }

    public Optional<Cart> readOneCart(Long id) {
        return cartsRepository.findById(id);
    }

    public void delete(Long id) {
        cartsRepository.deleteById(id);
    }



}
