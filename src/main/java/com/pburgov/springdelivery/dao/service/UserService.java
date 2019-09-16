package com.pburgov.springdelivery.dao.service;

import com.pburgov.springdelivery.dao.entity.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);
}

