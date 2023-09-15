package com.springboot.springsecurity.repository;

import com.springboot.springsecurity.entity.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MerchantMongoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public Merchant saveMerchant(Merchant merchant) {
        Merchant savedMerchant = mongoTemplate.save(merchant);

        return savedMerchant;
    }

    public Merchant updateMerchant(Merchant merchant) {
//		Merchant savedMerchant = mongoTemplate.save(merchant);

//		mongoTemplate.update(Merchant.class)
//	    .matching(where("id").is(merchant.id))
//	    .apply(new Update().push("books", book))
//	    .first();

//		return savedMerchant;
        return null;
    }

    public List<Merchant> findAllMerchants() {
        return mongoTemplate.findAll(Merchant.class);
    }

    public Merchant findMerchantById(String id) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(id)), Merchant.class);
    }

    public Merchant findMerchantByName(String name) {
        // TODO Auto-generated method stub
        Merchant merchants = mongoTemplate.findOne(
                Query.query(Criteria.where("merchantName").is(name)), Merchant.class);
        return merchants;
    }

    public String deleteMerchantById(String id) {
//		Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(
                Query.query(Criteria.where("id").is(id)), Merchant.class);
        return "User with id: " + id + " is successfully deleted";
    }
}
