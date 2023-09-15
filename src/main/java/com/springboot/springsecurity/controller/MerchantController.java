package com.springboot.springsecurity.controller;

import com.springboot.springsecurity.model.MerchantModel;
import com.springboot.springsecurity.model.ResponseModel;
import com.springboot.springsecurity.service.MerchantServiceImpl;
import com.springboot.springsecurity.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/merchant")
public class MerchantController {

    Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Autowired
    MerchantServiceImpl merchantServiceImpl;

    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping
    public ResponseEntity<ResponseModel<List<MerchantModel>>> getAllMerchants() {
        List<MerchantModel> merchantModelList = merchantServiceImpl.getAllMerchants();
        ResponseModel responseModel = ResponseModel.<List<MerchantModel>>builder()
                .data(merchantModelList)
                .message("get all the merchants")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping("/{merchantId}")
    public ResponseEntity<ResponseModel<MerchantModel>> getMerchantById(@PathVariable String merchantId) {
        MerchantModel merchantModelList = merchantServiceImpl.getMerchantById(merchantId);
        ResponseModel responseModel = ResponseModel.<MerchantModel>builder()
                .data(merchantModelList)
                .message("get all the merchants")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseModel<MerchantModel>> addMerchant(@RequestBody MerchantModel merchantModel) {
        MerchantModel addedMerchant = merchantServiceImpl.addMerchant(merchantModel);
        ResponseModel responseModel = ResponseModel.<MerchantModel>builder()
                .data(addedMerchant)
                .message("get all the merchants")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseModel<MerchantModel>> updateMerchant(@RequestBody MerchantModel merchantModel) {
        MerchantModel updatedMerchant = merchantServiceImpl.updateMerchant(merchantModel);
        ResponseModel responseModel = ResponseModel.<MerchantModel>builder()
                .data(updatedMerchant)
                .message("get all the merchants")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseModel<MerchantModel>> deleteMerchant(@PathVariable String id) {
        String deletedMerchant = merchantServiceImpl.deleteMerchant(id);
        ResponseModel responseModel = ResponseModel.<String>builder()
                .data(deletedMerchant)
                .message("get all the merchants")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

}
