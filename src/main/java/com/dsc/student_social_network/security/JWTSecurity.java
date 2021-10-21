package com.dsc.student_social_network.security;

import java.util.Date;

import com.dsc.student_social_network.dto.LoginResponseDto;
import com.dsc.student_social_network.entity.User;
import com.dsc.student_social_network.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTSecurity {

  @Autowired
  private UserService userService;
  public static final String TOKEN_KEY = "wdsjfhkwbfdgwuierhweij";

  public LoginResponseDto authenticate(User user) {
    if (!userService.validateUser(user)) {
      return new LoginResponseDto("Usuario ou senha invalidos. Nao foi realizado o login.");
    }
    String token = generateToken(user.getEmail());
    return new LoginResponseDto(token);
  }

  private String generateToken(String email) {
    return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
        .setExpiration(new Date(System.currentTimeMillis() + 3 * 60 * 1000)).compact();// 3 min
  }
}
