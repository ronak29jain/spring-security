package com.springboot.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterModel {

    private String productName;
    private int quanity;
    private int prize;

    private String merchantid;
}
