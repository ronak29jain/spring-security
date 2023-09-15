package com.springboot.springsecurity.security;

import com.springboot.springsecurity.entity.User;
import com.springboot.springsecurity.entity.UserMongo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String status;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(String email) {
        this.email = email;
    }

    public MyUserDetails(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.authorities = Arrays.stream(user.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public MyUserDetails(UserMongo userMongo) {
        this.firstName = userMongo.getFirstName();
        this.lastName = userMongo.getLastName();
        this.email = userMongo.getEmail();
        this.password = userMongo.getPassword();
        this.status = userMongo.getStatus();
        this.authorities = Arrays.stream(userMongo.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        if (status.equals("InActive")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        if (status.equals("Registered") || status.equals("InActive")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        if (status.equals("Active")) {
            return true;
        }
        return false;
    }

}
