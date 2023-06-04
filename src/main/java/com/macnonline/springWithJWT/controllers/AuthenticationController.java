package com.macnonline.springWithJWT.controllers;

import com.macnonline.springWithJWT.payload.request.LoginRequest;
import com.macnonline.springWithJWT.payload.request.SignUpRequest;
import com.macnonline.springWithJWT.payload.response.MessageResponse;
import com.macnonline.springWithJWT.security.JWTTokenProvider;
import com.macnonline.springWithJWT.service.CustomUsersDetailsService;
import com.macnonline.springWithJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;


    @PostMapping("signin")
    public ResponseEntity<Object> authenticatedUser(@RequestBody LoginRequest loginRequest, BindingResult bindingResult){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt="Bearer "+jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok("Authenticated "+jwt);
    }

    @PostMapping("signup")
    public ResponseEntity<Object>registerUser(@RequestBody SignUpRequest signUpRequest){
        userService.createUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));

    }
}
