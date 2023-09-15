package com.springboot.springsecurity.controller;

import com.springboot.springsecurity.model.*;
import com.springboot.springsecurity.service.AuthService;
import com.springboot.springsecurity.service.EmailSenderService;
import com.springboot.springsecurity.service.HomeService;
import com.springboot.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    HomeService homeService;

    @Autowired
    UserService userService;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    AuthService authService;

    @GetMapping("/google/user/register")
    public ResponseEntity<ResponseModel<HomeResponseModel>> homepage() {
        HomeResponseModel homeResponseModel = homeService.homeresponseService("Welcome to this amazing app");
        ResponseModel responseModel = ResponseModel.<HomeResponseModel>builder()
                .data(homeResponseModel)
                .message("hello")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<ResponseModel<DashboardResponseModel>> dashboard() {
        DashboardResponseModel homeResponseModel = homeService.dashboardResponseService("Welcome to dashboard");
        ResponseModel responseModel = ResponseModel.<DashboardResponseModel>builder()
                .data(homeResponseModel)
                .message("hello")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping("/manage")
    public ResponseEntity<ResponseModel<HomeResponseModel>> manage() {
        HomeResponseModel homeResponseModel = homeService.homeresponseService("Welcome to manage page");
        ResponseModel responseModel = ResponseModel.<HomeResponseModel>builder()
                .data(homeResponseModel)
                .message("hello")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<ResponseModel<AuthResponse>> auth(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        AuthResponse authResponse = authService.auth(authRequest);
        ResponseModel responseModel = ResponseModel.<AuthResponse>builder()
                .data(authResponse)
                .message("Authentication done and jwt token is provided")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<ResponseModel<HomeResponseModel>> getAllUsers() {
        ArrayList<UserModel> userModelList = userService.getAllUsers();
        ResponseModel responseModel = ResponseModel.<ArrayList<UserModel>>builder()
                .data(userModelList)
                .message("All users list")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseModel<UserModel>> registration(@Valid @RequestBody UserRegistrationModel userRegistrationModel) {
        UserModel userModel = userService.register(userRegistrationModel);
        ResponseModel responseModel = ResponseModel.<UserModel>builder()
                .data(userModel)
                .message("User Registered")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping("/confirm/{token}")
    public ResponseEntity<ResponseModel<String>> confirmEmailAddress(@PathVariable String token) {
        String status = userService.confirmEmailAddress(token);
        ResponseModel responseModel = ResponseModel.<String>builder()
                .data(status)
                .message("User confirmed and credentials sent")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @DeleteMapping("/register")
    public ResponseEntity<ResponseModel<UserModel>> deleteUser(@PathVariable String email) {
        DeleteUserModel deleteUserModel = userService.deleteUser(email);
        ResponseModel responseModel = ResponseModel.<DeleteUserModel>builder()
                .data(deleteUserModel)
                .message("Deletion Successful")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @GetMapping("/forgotpassword/{email}")
    public ResponseEntity<ResponseModel<String>> forgotpassword(@PathVariable String email) {
        String string = userService.forgotpassword(email);
        ResponseModel responseModel = ResponseModel.<String>builder()
                .data(string)
                .message("All users list")
                .build();
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailDetails details) {
        String status = emailSenderService.sendSimpleEmail(details);
        return status;
    }

}
