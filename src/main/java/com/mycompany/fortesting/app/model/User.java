/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="users")
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity {
    
    @Column(name="login", length=50, nullable=false, unique=true)
    private String login;
    
    @Column(name = "first_name", length=50, nullable=true)
    private String firstName;

    @Column(name = "last_name", length=50, nullable=true)
    private String lastName;
    
    @Column(name="dob", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob; // day of birthday

    @Column(name = "email", length=50, nullable=true)
    private String email;

    @Column(name = "password", length=250, nullable=false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length=10, nullable=true)
    private Gender gender;
 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
