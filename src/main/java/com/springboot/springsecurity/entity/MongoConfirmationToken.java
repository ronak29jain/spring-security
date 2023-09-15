package com.springboot.springsecurity.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "MongoConfirmationToken")
public class MongoConfirmationToken {

    @Id
    private Long id;
    private String email;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;


//	private User user;
}
