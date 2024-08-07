package com.keflastore.kfstr.services;

import com.keflastore.kfstr.entities.Cart;

import com.keflastore.kfstr.entities.Client;


import com.keflastore.kfstr.entities.Product;
import com.keflastore.kfstr.repositories.CartsRepository;
import com.keflastore.kfstr.repositories.ClientsRepository;
import com.keflastore.kfstr.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsService {
    @Autowired
    private CartsRepository cartsRepository;
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private ProductsRepository productsRepository;

    //Agregar prod al carrito de un cliente
    public Cart addProduct(Long clientId, Long productId, Integer quantity) {
        Optional<Client> client = clientsRepository.findById(clientId);
        Optional<Product> product = productsRepository.findById(productId);
        if (client.isPresent() & product.isPresent()) {
            Cart cart = new Cart();
            cart.setClient(client.get());
            cart.setProduct(product.get());
            cart.setQuantity(quantity);

            cart.setPrice(product.get().getPrice());
            cart.setState(false);
            return cartsRepository.save(cart);
        }else {
            throw new RuntimeException("client or product not found");
            }
    }

    //Quitar prod del carrito
    public Cart removeProduct(Long cartId) {
        Optional<Cart> cart = cartsRepository.findById(cartId);
        if (cart.isPresent()) {
            cartsRepository.deleteById(cartId);
            return cart.get();
        }else{
            throw new RuntimeException("cart not found");
        }
    }

    //Buscar carrito de cliente
    public List<Cart> findByClientIdAndState(Long clientId) {
        List<Cart> carts = cartsRepository.findByClientIdAndState(clientId, false);
        if (carts.isEmpty()) {
            throw new RuntimeException("cart not found");
        }else {
            return carts;
        }
    }

    //Mostrar todos los carritos
    public List<Cart> findAllCarts (){
        return cartsRepository.findAll();
    }

    //Actualizar un carrito
    public Cart updateCart(Long id, Cart cartDetails) {
        return cartsRepository.findById(id).map(cart -> {
            if (cartDetails.getQuantity() != null) {
                cart.setQuantity(cartDetails.getQuantity());
            }
            if (cartDetails.getPrice() != null) {
                cart.setPrice(cartDetails.getPrice());
            }
            if (cartDetails.getState() != null) {
                cart.setState(cartDetails.getState());
            }
            if (cartDetails.getProduct() != null) {
                cart.setProduct(cartDetails.getProduct());
            }
            if (cartDetails.getClient() != null) {
                cart.setClient(cartDetails.getClient());
            }
            return cartsRepository.save(cart);
        }).orElseGet(() -> {
            cartDetails.setId(id);
            return cartsRepository.save(cartDetails);
        });
    }

}


