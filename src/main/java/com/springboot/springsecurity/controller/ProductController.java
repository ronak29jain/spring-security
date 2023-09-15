package com.springboot.springsecurity.controller;

import com.springboot.springsecurity.model.ProductModel;
import com.springboot.springsecurity.model.ProductRegisterModel;
import com.springboot.springsecurity.model.ResponseModel;
import com.springboot.springsecurity.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping
    public ResponseEntity<ResponseModel<List<ProductModel>>> getAllProducts() {
        List<ProductModel> productModelList = productServiceImpl.getAllProducts();
        ResponseModel responseModel = ResponseModel.<List<ProductModel>>builder()
                .data(productModelList)
                .message("get all the products")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel<ProductModel>> registerProduct(@RequestBody ProductRegisterModel productRegisterModel) {
        ProductRegisterModel registedProduct = productServiceImpl.registerProduct(productRegisterModel);
        ResponseModel responseModel = ResponseModel.<ProductRegisterModel>builder()
                .data(registedProduct)
                .message("Product Saved in the database")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseModel<ProductModel>> updateProduct(@RequestBody ProductModel productModel) {
        ProductModel updatedProduct = productServiceImpl.updateProduct(productModel);
        ResponseModel responseModel = ResponseModel.<ProductModel>builder()
                .data(updatedProduct)
                .message("get all the products")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseModel<ProductModel>> deleteProduct(@PathVariable String id) {
        String deletedProduct = productServiceImpl.deleteProduct(id);
        ResponseModel responseModel = ResponseModel.<String>builder()
                .data(deletedProduct)
                .message("get all the products")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

}
