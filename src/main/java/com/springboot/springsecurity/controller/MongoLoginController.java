package com.springboot.springsecurity.controller;

import com.springboot.springsecurity.model.ResponseModel;
import com.springboot.springsecurity.model.UserMongoModel;
import com.springboot.springsecurity.model.UserRegistrationModel;
import com.springboot.springsecurity.service.UserMongoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/mongo")
public class MongoLoginController {

    Logger logger = LoggerFactory.getLogger(MongoLoginController.class);

    @Autowired
    UserMongoService userMongoService;

    @GetMapping("/")
    public ResponseEntity<ResponseModel<ArrayList<UserMongoModel>>> getAllMongoUsers() {
        logger.debug("getAllMongoUsers controller started running");
        ArrayList<UserMongoModel> userlist = userMongoService.getAllUsers();
        ResponseModel responseModel = ResponseModel.<ArrayList<UserMongoModel>>builder()
                .data(userlist)
                .message("User List from mongodb")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseModel<UserMongoModel>> registration(@Valid @RequestBody UserRegistrationModel userRegistrationModel) {
        UserMongoModel userMongoModel = userMongoService.register(userRegistrationModel);
        ResponseModel responseModel = ResponseModel.<UserMongoModel>builder()
                .data(userMongoModel)
                .message("User Registered")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}
