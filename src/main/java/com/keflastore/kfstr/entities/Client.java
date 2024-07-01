package com.keflastore.kfstr.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "clients")
@NoArgsConstructor @ToString @EqualsAndHashCode
//const con todos los argumentos
//@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private String name;

    @Getter @Setter private String lastname;

    @Getter @Setter private Integer age;

    @Getter @Setter private Integer docnumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter @Setter private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter @Setter private List<Invoice> invoices = new ArrayList<>();
}
