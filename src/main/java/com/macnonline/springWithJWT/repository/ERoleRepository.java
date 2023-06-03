package com.macnonline.springWithJWT.repository;

import com.macnonline.springWithJWT.entity.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ERoleRepository extends JpaRepository<ERole,Long> {
}
