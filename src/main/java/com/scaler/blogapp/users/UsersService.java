package com.scaler.blogapp.users;

import com.scaler.blogapp.security.JWTService;
import com.scaler.blogapp.users.dtos.CreateUserRequest;
import com.scaler.blogapp.users.dtos.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private UsersRepository usersRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.modelMapper= modelMapper;
        this.passwordEncoder=passwordEncoder;

    }

    public UserEntity createUser(CreateUserRequest req){
        UserEntity newUser = modelMapper.map(req,UserEntity.class);

        newUser.setPassword(passwordEncoder.encode(req.getPassword()));
       /* var newUser = UserEntity.builder()
                .username(req.getUsername())
//                .password(req.getPassword())
                .email(req.getEmail())
                .build();*/
        return usersRepository.save(newUser);

    }

    public Optional<UserEntity> getUser(String username){
        return usersRepository.findByUsername(username);
    }

    public UserEntity getUser(Long userId){
        return usersRepository.findById(userId) .orElseThrow(() -> new UserNotFoundException(userId));

    }

    public UserEntity loginUser(String Username,String Password){
        var user = usersRepository.findByUsername(Username).orElseThrow(() -> new UserNotFoundException(Username));
        var passMatch= passwordEncoder.matches(Password,user.getPassword());
        if(!passMatch) throw new InvalidCredentialsException();

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

    public static class InvalidCredentialsException extends IllegalArgumentException{

        public InvalidCredentialsException() {
            super("Invalid username or password");
        }
    }
}
