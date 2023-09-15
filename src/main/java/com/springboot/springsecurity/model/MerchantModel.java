package com.springboot.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantModel {

    private String merchantName;
    private String companyName;
    private String email;
    private Long phoneNumber;

    private List<ProductModel> products;
//	private List<ProductModel> products;

    private AddressModel address;
}
