package com.keflastore.kfstr.controllers;

import com.keflastore.kfstr.entities.Cart;
import com.keflastore.kfstr.services.CartsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@Tag(name = "Cart routes", description = "CRUD of carts")
public class CartsController {

    @Autowired
    private CartsService cartsService;

    @Operation(summary = "generate and add a product to cart",
            description = "Generate a  cart of a client and add a product")
    @ApiResponse(responseCode = "200",description = "cart generated successfully"
            ,content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cart.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid client ID format\" }")))
    @ApiResponse(responseCode = "404", description = "Client or product not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Cart not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @PostMapping("/{clid}/{pid}/{quantity}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long clid, @PathVariable Long pid, @PathVariable Integer quantity) {
        try {
            Cart cart = cartsService.addProduct(clid, pid, quantity);
            return ResponseEntity.status(HttpStatus.CREATED).body(cart);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Update cart by ID",
            description = "returns the cart updated")
    @ApiResponse(responseCode = "201",description = "cart updated successfully"
            ,content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cart.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid cart ID or body format\" }")))
    @ApiResponse(responseCode = "404", description = "ID not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Cart not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @PutMapping("/cart/{cid}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long cid, @RequestBody Cart cart) {
        try {
            Cart updatedCart = cartsService.updateCart(cid, cart);
            return ResponseEntity.ok(updatedCart);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "remove product from cart",
            description = "remove a product from the client's cart")
    @ApiResponse(responseCode = "200",description = "cart removed successfully"
            ,content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cart.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid client ID format\" }")))
    @ApiResponse(responseCode = "404", description = "cart not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Cart not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Cart> removeProductFromCart(@PathVariable Long cartId) {
        try {
            cartsService.removeProduct(cartId);
            return ResponseEntity.noContent().build();
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "find carts by Client ID and State",
            description = "returns a list of carts for a given client ID with state in false")
    @ApiResponse(responseCode = "200",description = "successfully retrived list of carts"
            ,content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cart.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid client ID format\" }")))
    @ApiResponse(responseCode = "404", description = "carts not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Cart not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @GetMapping("/{clid}")
    public ResponseEntity<List<Cart>> findByClientIdAndState(@PathVariable Long clid){
        try {
            List<Cart> carts = cartsService.findByClientIdAndState(clid);
            return ResponseEntity.ok(carts);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "find all carts ",
            description = "returns a list of all carts saved")
    @ApiResponse(responseCode = "200",description = "successfully retrived list of carts"
            ,content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cart.class))))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid format\" }")))
    @ApiResponse(responseCode = "404", description = "carts not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Cart not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @GetMapping()
    public ResponseEntity<List<Cart>> findAllCarts(){
        try {
            List<Cart> carts = cartsService.findAllCarts();
            return ResponseEntity.status(HttpStatus.OK).body(carts);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    }






