package com.springboot.springsecurity.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotEmpty(message = "email field cann't be empty")
    @Email(message = "Enter a valid email address")
    private String email;

    //	@Min(value = 4, message = "Password cann't be less than 4 characters")
//	@Max(value = 12, message = "Password cann't be more than 12 characters")
    @Size(min = 4, max = 12, message = "something")
    @NotBlank(message = "password should be provided")
    private String password;
}