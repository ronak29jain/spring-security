package com.springboot.springsecurity.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products_test1")
public class Product {

    @Id
    private String id;

    private String productName;
    private int quanity;
    private int prize;

    @DocumentReference(lazy = true)
    private Merchant merchant;

    public Product(String productName, int quanity, int prize, Merchant merchant) {
        super();
        this.productName = productName;
        this.quanity = quanity;
        this.prize = prize;
        this.merchant = merchant;
    }
}
