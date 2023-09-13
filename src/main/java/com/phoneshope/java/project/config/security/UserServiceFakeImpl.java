package com.phoneshope.java.project.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceFakeImpl implements  UserService{
    private final List<AuthUser> users = List.of(
            new AuthUser()
    );
    @Override
    public Optional<UserDetails> findUserByUsername(String username) {
        //AuthUser authUser = new AuthUser("chan", "chan1230",RoleEnum.SALE.getGrantedAuthorities(),);
        return Optional.empty();
    }
}