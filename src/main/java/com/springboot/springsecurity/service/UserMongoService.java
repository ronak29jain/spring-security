package com.springboot.springsecurity.service;

import com.springboot.springsecurity.model.UserMongoModel;
import com.springboot.springsecurity.model.UserRegistrationModel;

import java.util.ArrayList;

public interface UserMongoService {

    public UserMongoModel register(UserRegistrationModel userRegistrationModel);

    public ArrayList<UserMongoModel> getAllUsers();
}
