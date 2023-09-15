package com.springboot.springsecurity.service;

import com.springboot.springsecurity.entity.Address;
import com.springboot.springsecurity.model.AddressModel;
import com.springboot.springsecurity.repository.AddressMongoTemplate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMongoTemplate addressMongoTemplate;

    @Override
    public AddressModel registerAddress(AddressModel addressModel) {
        // TODO Auto-generated method stub
        ModelMapper modelMapper = new ModelMapper();
        Address Address = modelMapper.map(addressModel, Address.class);
        Address savedAddress = addressMongoTemplate.saveAddress(Address);
        AddressModel AddressModelSaved = modelMapper.map(savedAddress, AddressModel.class);
        return AddressModelSaved;
//		return null;
    }

    @Override
    public Address saveAddress(Address address) {
        // TODO Auto-generated method stub
        Address savedAddress = addressMongoTemplate.saveAddress(address);
        return savedAddress;
    }


    @Override
    public List<AddressModel> getAllAddresss() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<AddressModel> getAddressById() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public Optional<AddressModel> getAddressByName() {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

}
