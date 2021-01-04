package com.a1.nextlocation;

import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.Data;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class DataTestClass {
    private Data data;

    @Before
    public void init(){
        data = new Data();
    }


    @Test
    public void testSetDistanceTraveled() {
        data.setDistanceTraveled(100);

        int expected = 100;
        assertEquals(expected, data.getDistanceTraveled(), 0.1);
    }

    @Test
    public void testSetLocationsVisited() {
        data.setLocationsVisited(16);

        int expected = 16;
        assertEquals(expected, data.getLocationsVisited());
    }

    @Test
    public void testSetTotalTime() {
        data.setTotalTime(120);

        int expected = 120;
        assertEquals(expected, data.getTotalTime());
    }

    @Test
    public void testSetCouponList() {
        List<Coupon> expected = new ArrayList<>();
        expected.add(new Coupon("CODE1", "REWARD1"));
        expected.add(new Coupon("CODE2", "REWARD2"));
        expected.add(new Coupon("CODE3", "REWARD3"));

        data.setCouponList(expected);
        assertEquals(expected, data.getCouponList());
    }

    @Test
    public void testNextLocation() {
        Location expected = new Location("name1", "cord1", "desc1", null);
        data.setNextLocation(expected);
        assertEquals(expected, data.getNextLocation());
    }

    @Test
    public void testLastLocation() {
        Location expected = new Location("name2", "cord2", "desc2", null);
        data.setLastLocation(expected);
        assertEquals(expected, data.getLastLocation());
    }

    @Test
    public void testCurrentRoute() {
        Route expected = new Route("testRoute1");
        data.setCurrentRoute(expected);
        assertEquals(expected, data.getCurrentRoute());
    }


}