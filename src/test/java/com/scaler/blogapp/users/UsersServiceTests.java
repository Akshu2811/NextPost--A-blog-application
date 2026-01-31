package com.scaler.blogapp.users;

import com.scaler.blogapp.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTests {
    @Autowired
    private UsersService usersService;


    @Test
    void can_create_users(){
        var user = usersService.createUser(new CreateUserRequest("akshita","password","akshita@gmail.com"));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("akshita",user.getUsername());



    }

}
