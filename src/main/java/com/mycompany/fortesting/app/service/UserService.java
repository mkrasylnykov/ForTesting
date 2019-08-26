/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.service;

import com.mycompany.fortesting.app.repository.RoleDao;
import com.mycompany.fortesting.app.repository.UserDao;
import com.mycompany.fortesting.app.model.Role;
import com.mycompany.fortesting.app.model.Status;
import com.mycompany.fortesting.app.model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Admin
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public User createUser(User user) {
        Role roleUser = roleDao.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setRoles(userRoles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setCreated(new Date());

        User registeredUser = userDao.save(user);
        return registeredUser;
    }
    
    public User getUserById(Long id) {
        return userDao.findOne(id);
    }
    
    public User getUserByLogin(String userLogin) {
        return userDao.findByLogin(userLogin);
    }
    
    public Iterable<User> getAllUsers() {
        return userDao.findAll();
    }
    
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
    
    public User updateUser(Long id, Status status) {
        User userFromDb = userDao.findOne(id);
        userFromDb.setStatus(status);
        userFromDb.setUpdated(new Date());
        User upadedUser = userDao.save(userFromDb);
        return upadedUser;
    }
    
}
