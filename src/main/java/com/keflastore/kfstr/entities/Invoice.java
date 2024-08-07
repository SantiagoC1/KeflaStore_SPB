package com.keflastore.kfstr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Entity @Table(name = "invoices")
@NoArgsConstructor @ToString  @EqualsAndHashCode
@Schema(description = "Represents a invoice")
public class Invoice {
    @Schema(description = "Unique identifier of invoice", example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter @Getter private Long id;

    @Schema(description = "Identifier of client", example ="2")
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    @Setter @Getter private Client client;

    @Schema(description = "date of the invoice", example ="2024-08-02 12:00")
    @Temporal(TemporalType.TIMESTAMP)
    @Setter @Getter private LocalDateTime date;

    @Schema(description = "total value of the purchase", example ="299")
    @Setter @Getter private Double totalAmount;


}
