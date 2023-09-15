package com.springboot.springsecurity.service;

import com.springboot.springsecurity.entity.ConfirmationToken;
import com.springboot.springsecurity.entity.User;
import com.springboot.springsecurity.model.*;
import com.springboot.springsecurity.repository.ConfirmationTokenRepository;
import com.springboot.springsecurity.repository.UserRepository;
import com.springboot.springsecurity.security.MyUserDetailsService;
import com.springboot.springsecurity.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepo;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    AuthService authService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public UserModel register(UserRegistrationModel userRegistrationModel) {
        // TODO Auto-generated method stub

        Optional<User> userOptional = userRepo.findByEmail(userRegistrationModel.getEmail());


        User checkUser = userOptional.orElse(null);

        if (checkUser != null) {
            throw new EntityExistsException("User is already present");
        }

        User user = new User();

        user.setFirstName(userRegistrationModel.getFirstName());
        user.setLastName(userRegistrationModel.getLastName());
        user.setEmail(userRegistrationModel.getEmail());

        String randompasswordString = com.springboot.springsecurity.util.PassyUtil.generatePassayPassword();
        user.setPassword(bCryptPasswordEncoder.encode(randompasswordString));

        user.setRole(userRegistrationModel.getRole());
        user.setStatus("Registered");

        userRepo.save(user);

        logger.info("user: " + user.getFirstName() + " has been registered");

        String token = UUID.randomUUID().toString();

        String emailConfirmationUrl = "http://localhost:8082/confirm/" + token;

        EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient(user.getEmail());
        emailDetails.setSubject("Account Confimation Email");
        emailDetails.setMsgBody("Your account has been created in the new app. To confirm your email please click on the below link."
                + "\r\n" + "Confirmation Link: " + emailConfirmationUrl);

        ConfirmationToken confirmationToken = new ConfirmationToken(
                user.getEmail(),
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(24),
                user
        );


        confirmationTokenRepo.save(confirmationToken);

        logger.info("token genrated and saved in db.");

        String status = emailSenderService.sendSimpleEmail(emailDetails);

        logger.info(status + "to: " + user.getEmail() + " with email varification link.");

        UserModel userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setRole(user.getRole());
        userModel.setStatus("Registered");


        return userModel;
    }

    @Override
    @Transactional
    public String confirmEmailAddress(String token) {
        // TODO Auto-generated method stub

        Optional<ConfirmationToken> confirmationTokenOptional = confirmationTokenRepo.findByToken(token);

        ConfirmationToken confirmationToken = confirmationTokenOptional
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Link Expired");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());

        Optional<User> userOptional = userRepo.findByEmail(confirmationToken.getEmail());
        User user = userOptional.get();
        user.setStatus("Active");

        String randompasswordString = com.springboot.springsecurity.util.PassyUtil.generatePassayPassword();
        user.setPassword(bCryptPasswordEncoder.encode(randompasswordString));

        confirmationTokenRepo.save(confirmationToken);
        userRepo.save(user);

        logger.info("User, " + user.getFirstName() + "'s email: " + user.getEmail() + " is varified.");
        logger.info("Account with email " + user.getEmail() + " is now active ");

        EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient(user.getEmail());
        emailDetails.setSubject("Account Confimation Email");
        emailDetails.setMsgBody("Your email has been varified for the 'Fantastic' app. Credential for your account are given below." + "\r\n" + "id: " + user.getEmail() + "\r\n" + "password: " + randompasswordString);

        String status = emailSenderService.sendSimpleEmail(emailDetails);

        logger.info(status + "with email and genrated password.");

        return "Email Confirmed and Credentials are sent to the Email address.";
    }

    @Override
    public DeleteUserModel deleteUser(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<UserModel> getAllUsers() {

        List<User> userList = userRepo.findAll();

        ArrayList<UserModel> userModelList = new ArrayList<UserModel>();

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            UserModel userModel = new UserModel();

            userModel.setEmail(user.getEmail());
            userModel.setFirstName(user.getFirstName());
            userModel.setLastName(user.getLastName());
            userModel.setRole(user.getRole());
            userModel.setStatus(user.getStatus());

            userModelList.add(userModel);
        }

        return userModelList;
    }

    @Override
    public String forgotpassword(String email) {
        // TODO Auto-generated method stub
        UserDetails userDetails = myUserDetailsService.loadUserByUsername(email);
        String tJwtToken = jwtUtil.forgotPasswordtoken(userDetails);

        EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient(email);
        emailDetails.setSubject("Reset Password");
        emailDetails.setMsgBody("This mail has been sent to reset your password. Please click the below link to change your password." + "\r\n");

        String status = emailSenderService.sendSimpleEmail(emailDetails);

        return null;
    }

    @Override
    public ChangePasswordResponse changepassword(ChangePasswordRequest changePasswordRequest) {
        // TODO Auto-generated method stub
        return null;
    }

}
