/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.service;

import com.mycompany.fortesting.app.repository.AvtosalonDao;
import com.mycompany.fortesting.app.repository.CarDao;
import com.mycompany.fortesting.app.repository.SaleDao;
import com.mycompany.fortesting.app.dto.CarDto;
import com.mycompany.fortesting.app.dto.SaleDto;
import com.mycompany.fortesting.app.model.Avtosalon;
import com.mycompany.fortesting.app.model.Car;
import com.mycompany.fortesting.app.model.Sale;
import com.mycompany.fortesting.app.model.Status;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CarService {
    @Autowired
    private CarDao carDao;
    
    @Autowired
    private SaleDao saleDao;
    
    @Autowired
    private AvtosalonDao avtosalonDao;
    
    @Transactional
    public Car createCar(CarDto newCar) {
        Avtosalon avs = avtosalonDao.findByName(newCar.getAvtosalon());
        
        Car car = new Car();
        car.setCreated(new Date());
        car.setStatus(Status.ACTIVE);

        car.setBrand(newCar.getBrand());
        car.setColor(newCar.getColor());
        car.setEnabled(Boolean.TRUE);
        car.setAvtosalon(avs);

        return carDao.save(car);
    }
    
    public Car getCarById(Long id) {
        return carDao.findOne(id);
    }
    
    public Car getCarByBrand(String brand) {
        return carDao.findByBrand(brand);
    }
    
    public Iterable<Car> getAllCars() {
        return carDao.findAll();
    }
    
    public void deleteCar(Long id) {
        Car carFromDb = carDao.findOne(id);
        if (null != carFromDb && carFromDb.getEnabled()) {
            carDao.delete(id);
        }
    }
    
    @Transactional
    public Car updateCar(CarDto newCar) {
        Avtosalon avs = avtosalonDao.findByName(newCar.getAvtosalon());
        
        Car carFromDb = carDao.findOne(newCar.getId());
        if (null != carFromDb && carFromDb.getEnabled()) {
            carFromDb.setUpdated(new Date());
            carFromDb.setStatus(Status.valueOf(newCar.getStatus()));
            
            carFromDb.setBrand(newCar.getBrand());
            carFromDb.setColor(newCar.getColor());
            carFromDb.setEnabled(newCar.getEnabled());
            carFromDb.setAvtosalon(avs);

            return carDao.save(carFromDb);
        }
        return null;
    }
    
    @Transactional
    public Car carSale(Long id, String saleName) {
        Car carFromDb = carDao.findOne(id);
        Sale sale = new Sale();
        if (null != carFromDb && carFromDb.getEnabled()) {
            carFromDb.setEnabled(Boolean.FALSE);
            carDao.save(carFromDb);
            
            sale.setSaleName(saleName);
            sale.setCar(carFromDb);
            sale.setSaleDate(new Date());
            saleDao.save(sale);
            
            return carFromDb;
        }
        return null;
    }
    
    public List<SaleDto> getAllSales() {
        List<SaleDto> saleList = new ArrayList();
        Iterator<Sale> it = saleDao.findAll().iterator();
        while(it.hasNext()){
            saleList.add(SaleDto.fromSale(it.next()));
        }
        
        return saleList;
    }
}
