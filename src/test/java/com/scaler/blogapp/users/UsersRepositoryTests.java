package com.scaler.blogapp.users;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTests {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    @Order(1)
    void can_create_users() {

        var user=UserEntity.builder()
        .username("akshita")
        .email("akshita@gmail.com")
        .build();
        usersRepository.save(user);
    }

    @Test
    @Order(2)
    void can_find_users() {
        var user=UserEntity.builder()
                .username("akshita")
                .email("akshita@gmail.com")
                .build();
        usersRepository.save(user);
        var users=usersRepository.findAll();
        Assertions.assertEquals(1,users.size());
    }
}
