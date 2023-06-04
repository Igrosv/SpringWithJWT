package com.macnonline.springWithJWT.service;

import com.macnonline.springWithJWT.entity.Users;
import com.macnonline.springWithJWT.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=usersRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User with username not found"));
        return userBuild(user);
    }
    public Users loadUserByUserId(Long id){
        Users users=usersRepository.findById(id)
                .orElseThrow(()->new UsernameNotFoundException("User with id not found"));
        return users;

    }

    private UserDetails userBuild(Users user) {
        List<?extends GrantedAuthority>authorities=user.getRole()
                .stream()
                .map(r->new SimpleGrantedAuthority(r.getDeclaringClass().getName()))
                .collect(Collectors.toList());
       return new Users(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                authorities
        );

         }
}
