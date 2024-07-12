package com.keflastore.kfstr.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @Getter @Setter private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<CartItem> items = new ArrayList<>();

}
