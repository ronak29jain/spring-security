package com.springboot.springsecurity.repository;

import com.springboot.springsecurity.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressMongoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public Address saveAddress(Address address) {
        Address savedAddress = mongoTemplate.save(address);
        return savedAddress;
    }

    public Address updateAddress(Address merchant) {
//		Merchant savedMerchant = mongoTemplate.save(merchant);

//		mongoTemplate.update(Merchant.class)
//	    .matching(where("id").is(merchant.id))
//	    .apply(new Update().push("books", book))
//	    .first();

//		return savedMerchant;
        return null;
    }

    public List<Address> findAllAddresses() {
        return mongoTemplate.findAll(Address.class);
    }

    public Address findAddressById(Long id) {
//		return mongoTemplate.find(query, null, null);
        return null;
    }
}
