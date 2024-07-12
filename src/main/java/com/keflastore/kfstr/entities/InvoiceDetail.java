package com.keflastore.kfstr.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "invoices_details")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    @Getter @Setter private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Getter @Setter private Product product;

    @Getter @Setter private Integer quantity;

    @Getter @Setter private double price;

}
