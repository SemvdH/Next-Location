package com.a1.nextlocation;

import com.a1.nextlocation.json.DirectionsResult;
import com.a1.nextlocation.json.DirectionsStep;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DirectionsResultTest {
    private DirectionsResult directionsResult;

    @Before
    public void init(){
        directionsResult = new DirectionsResult();
    }

    @Test
    public void testDistance(){
        directionsResult.setDistance(45.32);
        double expected = 45.32;

        assertEquals(expected, directionsResult.getDistance(), 0.01);
    }

    @Test
    public void testDuration(){
        directionsResult.setDuration(95.123);
        double expected = 95.123;

        assertEquals(expected, directionsResult.getDuration(), 0.01);
    }

    @Test
    public void testSteps(){
        List<DirectionsStep> expected = new ArrayList<>();
        directionsResult.addStep(new DirectionsStep());
        directionsResult.addStep(new DirectionsStep());
        directionsResult.addStep(new DirectionsStep());
        directionsResult.setSteps(expected);

        assertEquals(expected, directionsResult.getSteps());
    }


}
