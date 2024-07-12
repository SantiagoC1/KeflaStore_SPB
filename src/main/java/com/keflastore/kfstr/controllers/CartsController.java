package com.keflastore.kfstr.controllers;

import com.keflastore.kfstr.entities.Cart;
import com.keflastore.kfstr.services.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carts")
public class CartsController {

    @Autowired
    private CartsService cartsService;

    //CREAR
    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartsService.save(cart);
    }

    //AGREGAR PROD AL CARRITO
    @PostMapping("/{cartId}/add-product")
    public ResponseEntity<String> addProductToCart(
            @PathVariable Long cartId,
            @RequestParam Long productId,
            @RequestParam Long clientId) {
        try {
            cartsService.addProductToCart(cartId, productId, clientId);
            return ResponseEntity.ok("Producto agregado al carrito exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //LEER TODOS
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartsService.findAll();
    }

    //LEER UNO
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Optional<Cart> cart = cartsService.readOneCart(id);
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    //ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cartDetails) {
        Optional<Cart> cart = cartsService.readOneCart(id);
        if (cart.isPresent()) {
            Cart existingCart = cart.get();
            existingCart.setClient(cartDetails.getClient());
            existingCart.setItems(cartDetails.getItems());
            return ResponseEntity.ok(cartsService.save(existingCart));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartsService.delete(id);
        return ResponseEntity.noContent().build();
    }






}
