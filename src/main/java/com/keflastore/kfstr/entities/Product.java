package com.keflastore.kfstr.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "products")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;
    @Getter @Setter private double price;
    @Getter @Setter private Integer stock;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter @Setter private List<Cart> carts= new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter @Setter private List<InvoiceDetail> invoiceDetails=new ArrayList<>();

}
