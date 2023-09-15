package com.springboot.springsecurity.service;

import com.springboot.springsecurity.entity.Product;
import com.springboot.springsecurity.model.ProductModel;
import com.springboot.springsecurity.model.ProductRegisterModel;

import java.util.List;


public interface ProductService {

    public ProductRegisterModel registerProduct(ProductRegisterModel productRegisterModel);

    public Product saveProduct(Product product);

    public List<ProductModel> getAllProducts();

    public ProductModel getProductById();

    public ProductModel getProductByName();

    public ProductModel addProduct(ProductModel productModel);

    public ProductModel updateProduct(ProductModel productModel);

    public String deleteProduct(String id);
}
