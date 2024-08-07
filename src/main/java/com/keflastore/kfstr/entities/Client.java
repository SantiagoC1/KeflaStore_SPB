package com.keflastore.kfstr.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "clients")
@NoArgsConstructor @ToString @EqualsAndHashCode
@Schema(description = "Represents a client")
public class Client {
    @Schema(description = "Unique identifier of client", example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Schema(description = "Name of the client", example ="Peter")
    @Getter @Setter private String name;

    @Schema(description = "Last name of the client", example ="Parker")
    @Getter @Setter private String lastname;

    @Schema(description = "Age of the client", example ="32")
    @Getter @Setter private Integer age;

    @Schema(description = "Email of the client", example ="spider_man@gmai.com")
    @Getter @Setter private String email;

    @Schema(description = "Phone of the client", example ="2214433222")
    @Getter @Setter private String phone;

    @Schema(description = "Adress of the client", example ="calle 7, 324")
    @Getter @Setter private String address;

    @Schema(description = "City of the clientt", example ="La Plata")
    @Getter @Setter private String city;

    @Schema(description = "Country of the client", example ="Argentina")
    @Getter @Setter private String country;

    @Schema(description = "DNI of the client", example ="224433")
    @Getter @Setter private Integer docnumber;

    @Schema(description = "Carts list of client", example =" ")
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter @Setter private List<Cart> carts = new ArrayList<>();

    @Schema(description = "Invoices list of client", example = " ")
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,orphanRemoval = true)
    @Getter @Setter private List<Invoice> invoices = new ArrayList<>();
}
