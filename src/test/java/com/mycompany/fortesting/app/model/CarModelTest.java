/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortesting.app.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class CarModelTest {
    private static Car car1;
    private static Car car2;
    private static Car car3;
    private static Car car4;
    
    public CarModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        car1 = new Car();
        car1.setId(10L);
        car1.setBrand("BMW");
        car1.setColor("Red");
        car1.setEnabled(Boolean.TRUE);
        
        car2 = new Car();
        car2.setId(11L);
        car2.setBrand("BMW");
        car2.setColor("Red");
        car2.setEnabled(Boolean.TRUE);
        
        car3 = new Car();
        car3.setId(12L);
        car3.setBrand("AUDI");
        car3.setColor("Black");
        car3.setEnabled(Boolean.TRUE);
        
        car4 = new Car();
        car4.setId(13L);
        car4.setBrand("BMW");
        car4.setColor("Red");
        car4.setEnabled(Boolean.TRUE);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void carEquals() {
        assertEquals(car1, car2);
    }
    
    @Test
    public void carNotEquals() {
        assertNotEquals(car1, car3);
    }
    
    // Additional tests
    @Test
    public void reflexive() {
        assertEquals(car1, car1);
    }
    
    @Test
    public void symmetric() {
        assertEquals(car1, car2);
        assertEquals(car2, car1);
    }
    
    @Test
    public void transitive() {
        assertEquals(car1, car2);
        assertEquals(car2, car4);
        assertEquals(car1, car4);
    }
    
    public void consistent() {
        for(int i=0; i > 10; i++){ 
            assertEquals(car1, car2);
        }
    }
}
