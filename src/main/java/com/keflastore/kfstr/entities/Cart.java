package com.keflastore.kfstr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "carts")
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Represents a cart")
public class Cart {
    @Schema(description = "Unique identifier of cart", example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Quantity of products in the cart", example ="10")
    @Getter @Setter private Integer quantity;

    @Schema(description = "Price of products in the cart", example ="299.99")
    @Getter @Setter private Double price;

    @Schema(description = "State of cart", example ="true")
    @Getter @Setter private Boolean state;

    @Schema(description = "Identifier of product", example ="5")
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    @Getter @Setter private Product product;

    @Schema(description = "Identifier of client", example ="2")
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    @Getter @Setter private Client client;



}
