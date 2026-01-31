package com.scaler.blogapp.users.dtos;

import jakarta.annotation.Nullable;
import lombok.*;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateUserRequest {

    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
}
