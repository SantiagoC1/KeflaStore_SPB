package com.keflastore.kfstr.controllers;


import com.keflastore.kfstr.entities.Product;
import com.keflastore.kfstr.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
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
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
@Tag(name = "Product routes", description = "CRUD of products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    //CREAR
    @Operation(summary = "generate a product",
            description = "Generate a  product")
    @ApiResponse(responseCode = "201", description = "product generated successfully"
            , content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid body format\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productsService.create(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //LEER TODOS
    @Operation(summary = "get list of products",
            description = "returns a list of products")
    @ApiResponse(responseCode = "200", description = "products retrived successfully"
            , content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid format\" }")))
    @ApiResponse(responseCode = "404", description = "Products not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"list of products not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productsService.readAll();
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //LEER UNO
    @Operation(summary = "get a product by id",
            description = "returns the product with the given ID")
    @ApiResponse(responseCode = "200", description = "product retrived successfully"
            , content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid ID format\" }")))
    @ApiResponse(responseCode = "404", description = "product not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Product not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @GetMapping("/{pid}")
    public ResponseEntity<Product> getProductById(@PathVariable Long pid) {
        try {
            Optional<Product> product = productsService.readOne(pid);
            return product.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(()
                    -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    //ACTUALIZAR
    @Operation(summary = "update product by ID",
            description = "returns the product with the given ID")
    @ApiResponse(responseCode = "201", description = "product updated successfully"
            , content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid ID or body format\" }")))
    @ApiResponse(responseCode = "404", description = "ID not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Product ID not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @PutMapping("/{pid}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long pid, @RequestBody Product product) {
        try {
            Optional<Product> updatedProduct = productsService.updateProduct(pid, product);
            if (updatedProduct.isPresent()) {
                return ResponseEntity.ok(updatedProduct.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //ELIMINAR
    @Operation(summary = "delete a product by ID",
            description = "Deletes the product with the given ID")
    @ApiResponse(responseCode = "200", description = "Product deleted successfully",
            content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Invalid ID format\" }")))
    @ApiResponse(responseCode = "404", description = "Product not found",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"Product not found\" }")))
    @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{ \"error\": \"An unexpected error occurred\" }")))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            boolean deleted = productsService.deleteOne(id);
            if (deleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
