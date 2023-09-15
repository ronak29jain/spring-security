package com.springboot.springsecurity.repository;

import com.springboot.springsecurity.entity.Merchant;
import com.springboot.springsecurity.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductMongoTemplate {

    @Autowired
    MongoTemplate mongoTemplate;

    public Product saveProduct(Product product) {
        Product savedProduct = mongoTemplate.save(product);
        return savedProduct;
    }

    public Product registerProduct(Product product) {
        Product savedProduct = mongoTemplate.save(product);

//		mongoTemplate.update(Merchant.class)
//		.matching(where("id").is(product.getMerchant().getId()))
//	    .apply(new Update().push("products", product))
//	    .first();

//		Update update = new Update();
//		update.set("name", "James");

        mongoTemplate.updateFirst(Query.query(
                        Criteria.where("id").is(product.getMerchant().getId())),
                new Update().push("products", product),
                Merchant.class);
        return savedProduct;
    }

    public Product updateProduct(Product product) {
//		Merchant savedMerchant = mongoTemplate.save(merchant);

//		mongoTemplate.update(Merchant.class)
//	    .matching(where("id").is(merchant.id))
//	    .apply(new Update().push("books", book))
//	    .first();

//		return savedMerchant;
        return null;
    }

    public List<Product> findAllProducts() {
        return mongoTemplate.findAll(Product.class);
    }

    public Product findProductById(Long id) {
//		return mongoTemplate.find(query, null, null);
        return null;
    }

}
