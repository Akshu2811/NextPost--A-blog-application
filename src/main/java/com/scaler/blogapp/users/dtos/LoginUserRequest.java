package com.scaler.blogapp.users.dtos;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
public class LoginUserRequest {

    private String username;
    private String password;
}
