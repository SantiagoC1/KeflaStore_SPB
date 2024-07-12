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

    public void addProductToCart(Long cartId, Long productId, Long clientId) {
        Optional<Cart> cartOptional = cartsRepository.findById(cartId);
        Optional<Product> productOptional = productsRepository.findById(productId);
        Optional<Client> clientOptional = clientsRepository.findById(clientId);

        if (cartOptional.isPresent() && productOptional.isPresent() && clientOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Product product = productOptional.get();
            Client client = clientOptional.get();

            // Asegurarse de que el producto y el cliente no sean nulos
            if (product == null || client == null) {
                throw new IllegalArgumentException("Producto o cliente no encontrado");
            }

            cart.setClient(client);
            cart.setProduct(product); // Establecer un solo producto en el carrito

            cartsRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Carrito, producto o cliente no encontrado");
        }
    }

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
