package com.studentregistration.studentregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

    private Long userId;
    private String userName;
    private String password;
    private String roles;
}
