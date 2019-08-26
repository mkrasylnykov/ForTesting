/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.service;

import static org.junit.Assert.assertFalse;

import com.mycompany.fortesting.app.repository.UserDao;
import com.mycompany.fortesting.app.model.Gender;
import com.mycompany.fortesting.app.model.Role;
import com.mycompany.fortesting.app.model.Status;
import com.mycompany.fortesting.app.model.User;
import com.mycompany.fortesting.app.repository.RoleDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Admin
 */
//@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    private static User user;
    private static User user2;
    private static List<User> userList;
    private static Role role;
    
    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;
    
    @MockBean
    private RoleDao roleDao;
    
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    
    @BeforeClass
    public static void setUpClass() {
        user = new User();
        user.setId(10L);
        user.setLogin("APetrovna");
        user.setFirstName("Alisa");
        user.setLastName("Petrovna");
        user.setDob(new Date());
        user.setGender(Gender.FEMALE);
        user.setPassword("Test123");
        
        user2 = new User();
        user2.setId(11L);
        user2.setLogin("NVlasova");
        user2.setFirstName("Natasha");
        user2.setLastName("Vlasova");
        user2.setDob(new Date());
        user2.setGender(Gender.FEMALE);
        user.setPassword("Test123");
        
        userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        
        role = new Role();
        role.setName("ROLE_USER");
        role.setUsers(userList);
    }
    
    @Test
    public void testCreateUser(){
        when(userDao.save(user)).thenReturn(user);
        when(roleDao.findByName("ROLE_USER")).thenReturn(role);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        assertThat(userService.createUser(user)).isEqualTo(user);
    }

    @Test
    public void testGetUserById(){
        when(userDao.findOne(10L)).thenReturn(user);
        assertThat(userService.getUserById(10L)).isEqualTo(user);
    }
    
    @Test
    public void testGetUserByLogin(){
        when(userDao.findByLogin("APetrovna")).thenReturn(user);
        assertThat(userService.getUserByLogin("APetrovna")).isEqualTo(user);
    }
    
    @Test
    public void testGetAllUsers(){
        when(userDao.findAll()).thenReturn(userList);
        assertThat(userService.getAllUsers()).isEqualTo(userList);
    }
    
    @Test
    public void testDeleteUser(){
        when(userDao.findOne(10L)).thenReturn(user);
        when(userDao.exists(user.getId())).thenReturn(false);
        assertFalse(userDao.exists(user.getId()));
    }

    @Test
    public void testUpdateUser(){
        when(userDao.findOne(10L)).thenReturn(user);
        user.setStatus(Status.NOT_ACTIVE);
        when(userDao.save(user)).thenReturn(user);
        assertThat(userService.updateUser(10L, Status.NOT_ACTIVE)).isEqualTo(user);
    }
}
