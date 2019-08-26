/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="avtosalons")
@Data
@EqualsAndHashCode(callSuper=false)
public class Avtosalon extends BaseEntity {
 
    @Column(name = "name", length=50, nullable=false)
    private String name;
}
