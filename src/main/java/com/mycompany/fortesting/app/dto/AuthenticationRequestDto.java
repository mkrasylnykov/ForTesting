/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.dto;

import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;   
}
