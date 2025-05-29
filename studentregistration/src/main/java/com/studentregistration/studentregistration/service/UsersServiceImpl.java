package com.studentregistration.studentregistration.service;

import com.studentregistration.studentregistration.dto.DeptDto;
import com.studentregistration.studentregistration.dto.UsersDto;
import com.studentregistration.studentregistration.exception.ResourceNotFoundException;
import com.studentregistration.studentregistration.mapper.CourseMapper;
import com.studentregistration.studentregistration.mapper.DeptMapper;
import com.studentregistration.studentregistration.mapper.UsersMapper;
import com.studentregistration.studentregistration.model.Course;
import com.studentregistration.studentregistration.model.Department;
import com.studentregistration.studentregistration.model.Users;
import com.studentregistration.studentregistration.repository.DeptRepository;
import com.studentregistration.studentregistration.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersDto createUsersDto(UsersDto usersDto) {
        Users users = UsersMapper.mapToUsers(usersDto);

        if(usersRepository.existsByUserName(usersDto.getUserName())) {
            throw new RuntimeException("User name already exists");
        }

        Users addUsers = usersRepository.save(users);
        return UsersMapper.mapToUsersDto(addUsers);
    }

    @Override
    public UsersDto getUserById(Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with the provided Id : "+ userId));
        return UsersMapper.mapToUsersDto(users);
    }

    @Override
    public List<UsersDto> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map((user) -> UsersMapper.mapToUsersDto(user)).collect(Collectors.toList());
    }

    @Override
    public UsersDto updateUser(Long userId, UsersDto updateusersDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with the provided Id : "+ userId));

        users.setUserName(updateusersDto.getUserName());
        users.setPassword(updateusersDto.getPassword());
        users.setRoles(updateusersDto.getRoles());

        Users updateUser = usersRepository.save(users);
        return UsersMapper.mapToUsersDto(updateUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with the provided Id : "+ userId));

        usersRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {

        usersRepository.deleteAll();
    }
}
