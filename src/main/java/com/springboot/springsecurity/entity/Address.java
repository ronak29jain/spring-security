package com.springboot.springsecurity.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "address_test1")
public class Address {

    @Id
    private String id;

    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;

    private Long pincode;

    @DocumentReference(lazy = true)
    private Merchant merchant;

    public Address(String line1, String line2, String city, String state, String country, Long pincode,
                   Merchant merchant) {
        super();
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.merchant = merchant;
    }

}
