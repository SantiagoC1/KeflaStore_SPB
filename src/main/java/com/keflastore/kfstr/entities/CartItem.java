package com.keflastore.kfstr.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @Getter @Setter private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;

    @Getter @Setter private Integer quantity;
}
