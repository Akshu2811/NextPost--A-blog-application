package com.scaler.blogapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private static final String JWT_KEY = "secret";
    private Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);

    public String createJWT(Long userId){
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
               // .withExpiresAt() // TODO:setup expiry parameter
                .sign(algorithm);
    }

    public Long retrieveUserId(String jwtString){

        var decodeJWT = JWT.decode(jwtString);
        var userId = Long.parseLong(decodeJWT.getSubject());
        return userId;

    }
}
