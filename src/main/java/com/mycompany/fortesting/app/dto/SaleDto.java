/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.dto;

import com.mycompany.fortesting.app.model.Sale;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
public class SaleDto {
    private Long carid;
    private String salename;
    
    public static SaleDto fromSale(Sale sale) {
        SaleDto saleDto = new SaleDto();
        saleDto.setCarid(sale.getId());
        saleDto.setSalename(sale.getSaleName());
        return saleDto;
    }
}
