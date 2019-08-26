/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.controller;

import com.mycompany.fortesting.app.dto.UserDto;
import com.mycompany.fortesting.app.model.Status;
import com.mycompany.fortesting.app.model.User;
import com.mycompany.fortesting.app.service.UserService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping(value="/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    // TBD - need change RequestBody to DTO
    @PostMapping(value="/create", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody User newUser){
        User user = userService.createUser(newUser);
        
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(value="/user/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserById(@PathVariable("id")Long id){
        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(value="/getUserByLogin/{login}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUserByLogin(@PathVariable("login")String login){
        User user = userService.getUserByLogin(login);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(value="/allusers", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getAllUsers(){
        Iterable<User> users = userService.getAllUsers();
        
        if (!users.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        // TBD - move next logic to service layer
        List<UserDto> userList = new ArrayList();
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            userList.add(UserDto.fromUser(it.next()));
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
    
    @DeleteMapping(value="/user/{id}")
    public void deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
    }
    
    //TBD - need use DTO
    @PutMapping(value="/user/{id}/status/{status:.+}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@PathVariable("id")Long id, @PathVariable("status")Status status){
        User user = userService.updateUser(id, status);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
