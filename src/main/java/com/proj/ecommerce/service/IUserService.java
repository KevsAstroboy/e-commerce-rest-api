package com.proj.ecommerce.service;

import com.proj.ecommerce.model.Role;
import com.proj.ecommerce.model.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IUserService {
    User saveUser(User user) throws Exception;

    Optional<User> findByUsername(String username) throws BadCredentialsException;


    @Transactional
    void makeAdmin(String username) throws Exception;

    void deleteByUsername(String username) throws Exception;
}
