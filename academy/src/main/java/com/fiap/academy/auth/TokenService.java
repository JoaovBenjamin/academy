package com.fiap.academy.auth;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fiap.academy.user.User;
import com.fiap.academy.user.UserRepository;

@Service
public class TokenService {
    
    @Autowired
    UserRepository repository;

    private Algorithm algorithm;

    public TokenService(UserRepository userRepository, @Value("${jwt.secret}") String secret){
        algorithm = Algorithm.HMAC256(secret);
        userRepository = this.repository;
    }

    public Token createToken(String email){
        var expirateAs = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
        String token = JWT.create()
                    .withSubject(email)
                    .withIssuer("academy")
                    .withExpiresAt(expirateAs)
                    .sign(algorithm);

        return new Token(token, "JWT");
    }

        public User getUserFromToken(String token) {
        var email = JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();

        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

