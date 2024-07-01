package com.keflastore.kfstr.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity @Table(name = "invoices")
@NoArgsConstructor @ToString  @EqualsAndHashCode
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Getter @Setter private LocalDateTime date;

    @Getter @Setter private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @Getter @Setter private Client client;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Getter @Setter private List<InvoiceDetail> invoiceDetails = new ArrayList<>();
}
