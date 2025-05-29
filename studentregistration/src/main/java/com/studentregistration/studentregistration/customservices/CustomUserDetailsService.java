package com.studentregistration.studentregistration.customservices;

import com.studentregistration.studentregistration.exception.ResourceNotFoundException;
import com.studentregistration.studentregistration.model.Users;
import com.studentregistration.studentregistration.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Users users = usersRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with the provided User Name : "+ username));
        return new User(users.getUserName(),users.getPassword(), Collections.singleton(new SimpleGrantedAuthority(users.getRoles())));
    }
}
