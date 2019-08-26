/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.repository;

import com.mycompany.fortesting.app.model.Sale;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Admin
 */
public interface SaleDao extends CrudRepository<Sale, Long>{
    Sale findBySaleName(String saleName);
}
