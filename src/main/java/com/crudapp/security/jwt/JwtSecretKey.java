package com.crudapp.security.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Data
@Configuration
@AllArgsConstructor
public class JwtSecretKey {

    private final JwtConfig config;

    @Bean
    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(config.getSecretKey().getBytes());
    }
}
