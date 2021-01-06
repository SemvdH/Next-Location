package com.a1.nextlocation;

import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

public class DataTest {
    private Data Data;

    @Before
    public void init(){
        Data = Data.INSTANCE;
    }

    @Test
    public void testDistance(){
        Data.addDistance(2356.234);
        double expected = 2356.234;
        assertEquals(expected, Data.getDistanceTraveled(), 0.01);
        Data.addDistance(234342.1);
        assertNotEquals(expected, Data.getDistanceTraveled());
    }

    @Test
    public void testTimeWalked(){
        Data.addTimeWalked(3456);
        long expected = 3456;
        assertEquals(expected, Data.getTimeWalked());
        Data.addTimeWalked(3445);
        assertNotEquals(expected, Data.getTimeWalked());
    }

    @Test
    public void testVisitedLocation(){
        Location testLocation = new Location("test", "test", "test", "test");
        Data.visitLocation(testLocation);
        int expected = 1;
        assertEquals(expected, Data.getLocationsVisited());
        Data.visitLocation(new Location("TESTFORFALSE", "TESTFORFALSE", "TESTFORFALSE", "TESTFORFALSE"));
        assertNotEquals(expected, Data.getLocationsVisited());
    }

}
