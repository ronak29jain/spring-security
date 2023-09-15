package com.springboot.springsecurity.service;

import com.springboot.springsecurity.model.AuthRequest;
import com.springboot.springsecurity.model.AuthResponse;
import com.springboot.springsecurity.security.MyUserDetailsService;
import com.springboot.springsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponse auth(AuthRequest authRequest) throws Exception {
        // TODO Auto-generated method stub
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            // TODO: handle exception
            throw new BadCredentialsException("Incorrect Email address or Password");
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthResponse(jwt);
    }

}
