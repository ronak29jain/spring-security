package com.springboot.springsecurity.service;

import com.springboot.springsecurity.model.EmailDetails;

public interface EmailSenderService {
    public String sendSimpleEmail(EmailDetails details);
}
