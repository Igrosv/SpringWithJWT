package com.macnonline.springWithJWT.payload.request;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
}
