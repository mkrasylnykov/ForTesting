/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="cars")
@Data
@EqualsAndHashCode(callSuper=false)
public class Car extends BaseEntity {
    
    @Column(name = "brand", length=50, nullable=false)
    private String brand;
    
    @Column(name = "color", length=50, nullable=true)
    private String color;
    
    @Column(name="enabled", nullable=false)
    private Boolean enabled;
    
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_avtosalon")
    private Avtosalon avtosalon;
}
