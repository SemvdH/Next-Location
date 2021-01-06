package com.a1.nextlocation;

import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.StaticData;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;

public class StaticDataTest {
    private StaticData staticData;

    @Before
    public void init(){
        staticData = StaticData.INSTANCE;
    }

    @Test
    public void testDistance(){
        staticData.addDistance(2356.234);
        double expected = 2356.234;
        assertEquals(expected, staticData.getDistanceTraveled(), 0.01);
        staticData.addDistance(234342.1);
        assertNotEquals(expected, staticData.getDistanceTraveled());
    }

    @Test
    public void testTimeWalked(){
        staticData.addTimeWalked(3456);
        long expected = 3456;
        assertEquals(expected, staticData.getTimeWalked());
        staticData.addTimeWalked(3445);
        assertNotEquals(expected, staticData.getTimeWalked());
    }

    @Test
    public void test(){
        Location testLocation = new Location("test", "test", "test", "test");
        staticData.visitLocation(testLocation);
        int expected = 1;
        assertEquals(expected, staticData.getLocationsVisited());
        staticData.visitLocation(new Location("TESTFORFALSE", "TESTFORFALSE", "TESTFORFALSE", "TESTFORFALSE"));
        assertNotEquals(expected, staticData.getLocationsVisited());
    }

}
