package com.scaler.blogapp.users;

import com.scaler.blogapp.users.dtos.CreateUserRequest;
import com.scaler.blogapp.users.dtos.LoginUserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity createUser(CreateUserRequest req){
        var newUser = UserEntity.builder()
                .username(req.getUsername())
//                .password(req.getPassword())// TODO: encrypt password
                .email(req.getEmail())
                .build();
        return usersRepository.save(newUser);

    }

    public Optional<UserEntity> getUser(String username){
        return usersRepository.findByUsername(username);
    }

    public UserEntity getUser(Long userId){
        return usersRepository.findById(userId) .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserEntity loginUser(LoginUserRequest req){
        var user = usersRepository.findByUsername(req.getUsername()).orElseThrow(() -> new UserNotFoundException(req.getUsername()));


        // TODO: match password
        return user;

    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(String username) {
            super("User "+username+" not found");
        }

        public UserNotFoundException(Long userId) {
            super("User with id: " + userId + " not found");
        }
    }
}
