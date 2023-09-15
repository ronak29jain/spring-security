package com.springboot.springsecurity.service;

import com.springboot.springsecurity.model.*;

import java.util.ArrayList;

public interface UserService {
    public UserModel register(UserRegistrationModel userRegistrationModel);

    public String confirmEmailAddress(String token);

    public DeleteUserModel deleteUser(String email);

    public ArrayList<UserModel> getAllUsers();

    public String forgotpassword(String email);

    public ChangePasswordResponse changepassword(ChangePasswordRequest changePasswordRequest);
}
