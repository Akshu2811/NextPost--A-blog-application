package com.scaler.blogapp.users.dtos;

import lombok.*;

@Data
public class LoginUserRequest {

    private String username;
    private String password;
}
