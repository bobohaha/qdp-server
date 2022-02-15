package com.tester.tester.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tester.tester.Entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String getToken(UserEntity userEntity) {

        String token;

        token= JWT.create().withAudience(userEntity.getUsername())
                .sign(Algorithm.HMAC256(userEntity.getPassword()));
        return token;
    }
}
