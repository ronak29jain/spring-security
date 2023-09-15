package com.springboot.springsecurity.security;

import com.springboot.springsecurity.entity.UserMongo;
import com.springboot.springsecurity.repository.UserMongoRepository;
import com.springboot.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserMongoRepository userMongoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

//		Optional<User> user = userRepo.findByEmail(email);
//		user.orElseThrow(() -> new UsernameNotFoundException("Not able to find: " + email));
//		return user.map(MyUserDetails::new).get();

        Optional<UserMongo> userMongo = userMongoRepository.findByEmail(email);
        userMongo.orElseThrow(() -> new UsernameNotFoundException("Not able to find: " + email));
        return userMongo.map(MyUserDetails::new).get();
    }

}
