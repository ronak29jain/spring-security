package com.springboot.springsecurity.service;

import com.springboot.springsecurity.entity.UserMongo;
import com.springboot.springsecurity.model.UserMongoModel;
import com.springboot.springsecurity.model.UserRegistrationModel;
import com.springboot.springsecurity.repository.ConfirmationTokenRepository;
import com.springboot.springsecurity.repository.UserMongoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMongoServiceImpl implements UserMongoService {

    Logger logger = LoggerFactory.getLogger(UserMongoServiceImpl.class);

    @Autowired
    UserMongoRepository userMongoRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public ArrayList<UserMongoModel> getAllUsers() {

        List<UserMongo> userList = userMongoRepository.findAll();

        ArrayList<UserMongoModel> userModelList = new ArrayList<UserMongoModel>();

        for (int i = 0; i < userList.size(); i++) {
            UserMongo userMongo = userList.get(i);
            UserMongoModel userMongoModel = new UserMongoModel();

            userMongoModel.setEmail(userMongo.getEmail());
            userMongoModel.setFirstName(userMongo.getFirstName());
            userMongoModel.setLastName(userMongo.getLastName());
            userMongoModel.setRole(userMongo.getRole());
            userMongoModel.setStatus(userMongo.getStatus());

            userModelList.add(userMongoModel);
        }

        return userModelList;
    }

    @Override
    @Transactional
    public UserMongoModel register(UserRegistrationModel userRegistrationModel) {
        // TODO Auto-generated method stub
        Optional<UserMongo> userOptional = userMongoRepository.findByEmail(userRegistrationModel.getEmail());


        UserMongo checkUser = userOptional.orElse(null);

        if (checkUser != null) {
            throw new EntityExistsException("User is already present");
        }

        UserMongo user = new UserMongo();

        user.setFirstName(userRegistrationModel.getFirstName());
        user.setLastName(userRegistrationModel.getLastName());
        user.setEmail(userRegistrationModel.getEmail());

//		String randompasswordString = com.springboot.springsecurity.util.PassyUtil.generatePassayPassword();
//		user.setPassword(bCryptPasswordEncoder.encode(randompasswordString));
        user.setPassword(bCryptPasswordEncoder.encode("password"));

        user.setRole(userRegistrationModel.getRole());
//		user.setStatus("Registered");
        user.setStatus("Active");

        userMongoRepository.save(user);

        logger.info("user: " + user.getFirstName() + " has been registered");

//		String token = UUID.randomUUID().toString();
//		
//		String emailConfirmationUrl = "http://localhost:8082/confirm/" + token;
//		
//		EmailDetails emailDetails = new EmailDetails();
//		
//		emailDetails.setRecipient(user.getEmail());
//		emailDetails.setSubject("Account Confimation Email");
//		emailDetails.setMsgBody("Your account has been created in the new app. To confirm your email please click on the below link."
//				+ "\r\n" + "Confirmation Link: " + emailConfirmationUrl);
//
//		ConfirmationToken confirmationToken = new ConfirmationToken(
//				user.getEmail(),
//				token,
//				LocalDateTime.now(),
//				LocalDateTime.now().plusHours(24),
//				user
//		);
//		
//		
//		confirmationTokenRepo.save(confirmationToken);
//		
//		logger.info("token genrated and saved in db.");
//
//		String status = emailSenderService.sendSimpleEmail(emailDetails);
//		
//		logger.info(status + "to: " + user.getEmail() + " with email varification link.");

        UserMongoModel userMongoModel = new UserMongoModel();
        userMongoModel.setEmail(user.getEmail());
        userMongoModel.setFirstName(user.getFirstName());
        userMongoModel.setLastName(user.getLastName());
        userMongoModel.setRole(user.getRole());
        userMongoModel.setStatus(user.getStatus());


        return userMongoModel;
//		return null;
    }
}
