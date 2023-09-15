package com.springboot.springsecurity.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "merchants_test1")
public class Merchant {

    @Id
    private String id;

    private String merchantName;
    private String companyName;
    private String email;
    private Long phoneNumber;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{'merchant':?#{#self._id} }")
//	@DocumentReference
    private List<Product> products;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{'merchant':?#{#self._id} }")
//	@DocumentReference
    private Address address;


    public Merchant(String merchantName, String companyName, String email, Long phoneNumber, List<Product> products, Address address) {
        super();
        this.merchantName = merchantName;
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.products = products;
        this.address = address;
    }


    public Merchant(String merchantName, String companyName, String email, Long phoneNumber) {
        super();
        this.merchantName = merchantName;
        this.companyName = companyName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


}
