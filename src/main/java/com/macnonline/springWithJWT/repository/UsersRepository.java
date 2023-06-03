package com.macnonline.springWithJWT.repository;

import com.macnonline.springWithJWT.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
   Optional<Users> findByUsername(String username);
}
