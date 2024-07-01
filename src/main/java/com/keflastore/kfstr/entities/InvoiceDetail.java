package com.keflastore.kfstr.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "invoicesDetails")
@NoArgsConstructor @ToString @EqualsAndHashCode
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private Integer quiantity;

    @Getter @Setter private Double unitPrice;

    @Getter @Setter private Double subTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    @Getter @Setter private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @Getter @Setter private Product product;

}
