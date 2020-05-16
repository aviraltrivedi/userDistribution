package com.demoapp.demo.repository;

import com.demoapp.demo.model.UserType;
import com.demoapp.demo.model.UserTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    Optional<UserType> findByUserType(UserTypes userType);
}
