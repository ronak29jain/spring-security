package com.springboot.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;

    private Long pincode;
}
