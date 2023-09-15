package com.springboot.springsecurity.repository;

import com.springboot.springsecurity.entity.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchantMongoRepository extends MongoRepository<Merchant, Long> {

}
