package com.springboot.springsecurity.service;

import com.springboot.springsecurity.model.DashboardResponseModel;
import com.springboot.springsecurity.model.HomeResponseModel;
import com.springboot.springsecurity.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HomeService {


    @Autowired
    UserMongoRepository userMongoRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public DashboardResponseModel dashboardResponseService(String message) {

//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DashboardResponseModel dashboardResponseModel = new DashboardResponseModel(message, user);
        return dashboardResponseModel;
    }


    public HomeResponseModel homeresponseService(String message) {
        HomeResponseModel homeResponseModel = new HomeResponseModel(message);
        return homeResponseModel;
    }
}
