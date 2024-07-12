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

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @Getter @Setter private Client client;

    @Temporal(TemporalType.TIMESTAMP)
    @Getter @Setter private LocalDateTime date;

    @Getter @Setter private double totalAmount;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter @Setter private List<InvoiceDetail> details = new ArrayList<>();
}
