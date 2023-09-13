package com.phoneshope.java.project.config.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    Optional <UserDetails> findUserByUsername(String username);
}
