/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.controller;

import com.mycompany.fortesting.app.dto.CarDto;
import com.mycompany.fortesting.app.dto.SaleDto;
import com.mycompany.fortesting.app.model.Car;
import com.mycompany.fortesting.app.model.Sale;
import com.mycompany.fortesting.app.service.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value="/api/cars")
public class CarController {
    
    @Autowired
    private CarService carService;
    
    @PostMapping(value="/createCar", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto){
        Car car = carService.createCar(carDto);
        
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        CarDto result = CarDto.fromCar(car);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(value="/car/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> getCarById(@PathVariable("id")Long id){
        Car car = carService.getCarById(id);

        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        CarDto result = CarDto.fromCar(car);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PutMapping(value="/updateCar", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto){
        Car car = carService.updateCar(carDto);
        
        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        CarDto result = CarDto.fromCar(car);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping(value="/saleCar", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> saleCar(@RequestBody SaleDto requestDto){
        Car car = carService.carSale(requestDto.getCarid(), requestDto.getSalename());

        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        CarDto result = CarDto.fromCar(car);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(value="/allsales", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SaleDto>> getAllSales(){
        List<SaleDto> sales = carService.getAllSales();
        
        if (!sales.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
}
