package com.macnonline.springWithJWT.service;

import com.macnonline.springWithJWT.entity.Users;
import com.macnonline.springWithJWT.entity.enam.ERole;
import com.macnonline.springWithJWT.payload.request.SignUpRequest;
import com.macnonline.springWithJWT.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(SignUpRequest signUpRequest){
        Users users = new Users();
        users.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        users.setUsername(signUpRequest.getUsername());
        users.getRole().add(ERole.ROLE_USER);
        usersRepository.save(users);
    }
}
