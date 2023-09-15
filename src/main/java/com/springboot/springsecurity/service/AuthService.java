package com.springboot.springsecurity.service;

import com.springboot.springsecurity.model.AuthRequest;
import com.springboot.springsecurity.model.AuthResponse;
import org.springframework.web.bind.annotation.RequestBody;


public interface AuthService {
    public AuthResponse auth(@RequestBody AuthRequest authRequest) throws Exception;
}
