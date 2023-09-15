package com.springboot.springsecurity.service;

import com.springboot.springsecurity.entity.Merchant;
import com.springboot.springsecurity.entity.Product;
import com.springboot.springsecurity.model.ProductModel;
import com.springboot.springsecurity.model.ProductRegisterModel;
import com.springboot.springsecurity.repository.MerchantMongoTemplate;
import com.springboot.springsecurity.repository.ProductMongoTemplate;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    MerchantMongoTemplate merchantMongoTemplate;

    @Autowired
    ProductMongoTemplate productMongoTemplate;

    @Override
    @Transactional
    public ProductRegisterModel registerProduct(ProductRegisterModel productRegisterModel) {
        // TODO Auto-generated method stub
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productRegisterModel, Product.class);
        Merchant merchant = merchantMongoTemplate.findMerchantById(productRegisterModel.getMerchantid());
        product.setMerchant(merchant);
        Product savedProduct = productMongoTemplate.registerProduct(product);
//		Product savedProduct = productMongoTemplate.saveProduct(product);


//		merchant.getProducts().add(product);
//		merchantMongoTemplate.saveMerchant(merchant);
        ProductRegisterModel productModelSaved = modelMapper.map(savedProduct, ProductRegisterModel.class);
        productModelSaved.setMerchantid(productRegisterModel.getMerchantid());
        return productModelSaved;
    }

    @Override
    public Product saveProduct(Product product) {
        // TODO Auto-generated method stub
        Product savedProduct = productMongoTemplate.saveProduct(product);
        return savedProduct;
    }

    @Override
    public List<ProductModel> getAllProducts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductModel getProductById() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductModel getProductByName() {
        // TODO Auto-generated method stub
        return null;
    }

    public ProductModel addProduct(ProductModel productModel) {
        // TODO Auto-generated method stub
        return null;
    }

    public ProductModel updateProduct(ProductModel productModel) {
        // TODO Auto-generated method stub
        return null;
    }

    public String deleteProduct(String id) {
        // TODO Auto-generated method stub
        return null;
    }


}
