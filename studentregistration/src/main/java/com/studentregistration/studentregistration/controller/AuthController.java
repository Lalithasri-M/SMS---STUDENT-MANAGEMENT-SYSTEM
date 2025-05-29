package com.studentregistration.studentregistration.controller;

import com.studentregistration.studentregistration.dto.UsersDto;
import com.studentregistration.studentregistration.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtToken jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UsersDto usersDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usersDto.getUserName(), usersDto.getPassword()));

            UserDetails userDetail = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetail);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid Username or Password"));
        }
    }
}
