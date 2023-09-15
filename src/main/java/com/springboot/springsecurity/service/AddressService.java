package com.springboot.springsecurity.service;

import com.springboot.springsecurity.entity.Address;
import com.springboot.springsecurity.model.AddressModel;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    public AddressModel registerAddress(AddressModel addressModel);

    public Address saveAddress(Address address);

    public List<AddressModel> getAllAddresss();

    public Optional<AddressModel> getAddressById();

    public Optional<AddressModel> getAddressByName();

}
