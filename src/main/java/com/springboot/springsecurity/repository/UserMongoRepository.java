package com.springboot.springsecurity.repository;

import com.springboot.springsecurity.entity.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<UserMongo, Long> {

    Optional<UserMongo> findByEmail(String email);


    List<UserMongo> findByFirstName(String firstName);

//	@Query("{'Address.city':? 0}")
//	List<UserMongo> findByCity(String city);	
}
