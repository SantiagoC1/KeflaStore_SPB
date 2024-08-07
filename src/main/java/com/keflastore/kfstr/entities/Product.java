package com.keflastore.kfstr.entities;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table (name = "products")
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Represents a product")
public class Product {
    @Schema(description = "Unique identifier of product", example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Name of product", example ="buzo oversize golf")
    @Getter @Setter private String name;

    @Schema(description = "description of product", example ="Comfortable pullover hoodie made from soft cotton blend, featuring a kangaroo pocket and adjustable drawstring hood")
    @Getter @Setter private String description;

    @Schema(description = "Category of the products", example ="abrigo")
    @Getter @Setter private String category;

    @Schema(description = "price of the product", example ="297.34")
    @Getter @Setter private Double price;

    @Schema(description = "stock of the product", example ="20")
    @Getter @Setter private Integer stock;




}
