package com.dsc.student_social_network.controller;

import javax.servlet.ServletException;

import com.dsc.student_social_network.dto.LoginResponseDto;
import com.dsc.student_social_network.entity.User;
import com.dsc.student_social_network.security.JWTSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

  @Autowired
  private JWTSecurity jwtSecurity;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDto> authenticate(@RequestBody User user) throws ServletException {
    return new ResponseEntity<>(jwtSecurity.authenticate(user), HttpStatus.OK);
  }
}
