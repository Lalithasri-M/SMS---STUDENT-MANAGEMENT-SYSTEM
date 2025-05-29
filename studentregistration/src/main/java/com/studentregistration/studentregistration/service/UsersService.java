package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.UsersDto;

import java.util.List;

public interface UsersService {

    UsersDto createUsersDto(UsersDto usersDto);

    UsersDto getUserById(Long userId);

    List<UsersDto> getAllUsers();

    UsersDto updateUser(Long userId, UsersDto updateusersDto);

    void deleteUserById(Long userId);

    void deleteAllUsers();
}
