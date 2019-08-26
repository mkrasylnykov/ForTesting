/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.security;

import com.mycompany.fortesting.app.model.User;
import com.mycompany.fortesting.app.security.jwt.JwtUser;
import com.mycompany.fortesting.app.security.jwt.JwtUserFactory;
import com.mycompany.fortesting.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class JwtUserDetailsService implements UserDetailsService{
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        User user = userService.getUserByLogin(userLogin);

        if (user == null) {
            throw new UsernameNotFoundException("Login: " + userLogin + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}
