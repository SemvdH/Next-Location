package com.a1.nextlocation;

import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

public class DataTest {
    private Data data;

    @Before
    public void init(){
        data = Data.INSTANCE;
    }

    @Test
    public void testDistance(){
        data.addDistance(2356.234);
        double expected = 2356.234;
        assertEquals(expected, data.getDistanceTraveled(), 0.01);
        data.addDistance(234342.1);
        assertNotEquals(expected, data.getDistanceTraveled());
    }

    @Test
    public void testTimeWalked(){
        data.addTimeWalked(3456);
        long expected = 3456;
        assertEquals(expected, data.getTotalTime());
        data.addTimeWalked(3445);
        assertNotEquals(expected, data.getTotalTime());
    }

    @Test
    public void testVisitedLocation(){
        Location testLocation = new Location("test", "test", "test", "test");
        data.visitLocation(testLocation);
        int expected = 1;
        assertEquals(expected, data.getLocationsVisited());
        data.visitLocation(new Location("TESTFORFALSE", "TESTFORFALSE", "TESTFORFALSE", "TESTFORFALSE"));
        assertNotEquals(expected, data.getLocationsVisited());
    }

    @Test
    public void testZoom(){
        data.setZoom(234.63);
        double expected = 234.63;
        assertEquals(expected, data.getZoom(), 0.01);
        data.setZoom(342.55);
        assertNotEquals(expected, data.getZoom());
    }

}
