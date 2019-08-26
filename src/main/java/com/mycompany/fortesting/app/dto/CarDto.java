/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.fortesting.app.model.Car;
import com.mycompany.fortesting.app.model.Status;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarDto {
    private Long id;
    private String brand;
    private String color;
    private Boolean enabled;
    private String avtosalon;
    private String status;
    
    public Car toCar() {
        Car car = new Car();
        car.setId(id);
        car.setBrand(brand);
        car.setColor(color);
        car.setEnabled(enabled);
        car.setStatus(Status.valueOf(status));
        return car;
    }

    public static CarDto fromCar(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setBrand(car.getBrand());
        carDto.setColor(car.getColor());
        carDto.setEnabled(car.getEnabled());
        carDto.setAvtosalon(car.getAvtosalon().getName());
        carDto.setStatus(car.getStatus().name());
        return carDto;
    }
}
