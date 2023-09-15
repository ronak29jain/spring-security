package com.springboot.springsecurity.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationModel {

    @Size(min = 3, max = 20, message = "first name should be 3 to 20 characters")
    @NotBlank(message = "first name cann't be black")
    private String firstName;

    @Size(min = 3, max = 20, message = "last name should be 3 to 20 characters")
    private String lastName;

    @Email(message = "Should be valid email")
    @NotBlank(message = "Email should be provided")
    private String email;

    @NotBlank(message = "should provide some role")
    private String role;
}
