package com.studentregistration.studentregistration.controller;

import com.studentregistration.studentregistration.dto.UsersDto;
import com.studentregistration.studentregistration.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUsers")
    public ResponseEntity<UsersDto> createUsers(@RequestBody UsersDto usersDto) {
        usersDto.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        UsersDto saveUsers = usersService.createUsersDto(usersDto);
        return new ResponseEntity<>(saveUsers, HttpStatus.CREATED);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable("userId") Long userId) {
        UsersDto usersDto = usersService.getUserById(userId);
        return new ResponseEntity<>(usersDto,HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        List<UsersDto> users = usersService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UsersDto> updateUser(@PathVariable("userId") Long userId, @RequestBody UsersDto updateUsersDto) {
        UsersDto usersDto = usersService.updateUser(userId,updateUsersDto);
        return new ResponseEntity<>(usersDto,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable("userId") Long userId) {
        usersService.deleteUserById(userId);
        return ResponseEntity.ok("User Deleted Successfully!...");
    }

    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<String> deleteAllUsers() {
        usersService.deleteAllUsers();
        return ResponseEntity.ok("Users Deleted Successfully!...");
    }
}
