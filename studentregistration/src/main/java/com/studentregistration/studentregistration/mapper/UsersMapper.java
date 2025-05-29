package com.studentregistration.studentregistration.mapper;

import com.studentregistration.studentregistration.dto.DeptDto;
import com.studentregistration.studentregistration.dto.UsersDto;
import com.studentregistration.studentregistration.model.Department;
import com.studentregistration.studentregistration.model.Users;

public class UsersMapper {

    public static UsersDto mapToUsersDto(Users users) {
        return new UsersDto(
                users.getUserId(),
                users.getUserName(),
                users.getPassword(),
                users.getRoles()
        );
    }

    public static Users mapToUsers(UsersDto usersDto) {
        return new Users(
                usersDto.getUserId(),
                usersDto.getUserName(),
                usersDto.getPassword(),
                usersDto.getRoles()
        );
    }

}
