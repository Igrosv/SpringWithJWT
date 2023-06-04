package com.macnonline.springWithJWT.entity;

import com.macnonline.springWithJWT.entity.enam.ERole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "users_role",
            joinColumns = @JoinColumn(name = "users_id"))
    private Set<ERole> role = new HashSet<>();
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public Users() {
    }


    public Users(Long id, String username, String password,
                 Set<ERole> role,
                 Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
