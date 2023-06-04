package com.macnonline.springWithJWT.security;

import com.macnonline.springWithJWT.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenProvider {

    public String generateToken(Authentication authentication){
        Users users= (Users) authentication.getPrincipal();
        String nameId = Long.toString(users.getId());

        Map<String,Object>claims=new HashMap<>();
        claims.put("id",nameId);
        claims.put("username",users.getUsername());

        return Jwts.builder()
                .setSubject(nameId)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512,"Secret secret")
                .compact();

    }
    public boolean checkToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey("Secret secret")
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException|
        IllegalArgumentException ex){
            ex.printStackTrace();
            return false;
        }

    }
    public Long getUserIdFromToken(String token){
        Claims claims=Jwts.parser()
                .setSigningKey("Secret secret")
                .parseClaimsJws(token)
                .getBody();

        return (long) Integer.parseInt(claims.getId());
    }

}
