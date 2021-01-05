package com.a1.nextlocation;

import com.a1.nextlocation.json.DirectionsStep;

import org.junit.Before;
import org.junit.Test;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DirectionsStepTest {
    private DirectionsStep directionsStep;
    @Before
    public void init(){
        directionsStep = new DirectionsStep();
    }

    @Test
    public void testDistance(){
        directionsStep.setDistance(432.56);
        double expected = 432.56;
        assertEquals(expected, directionsStep.getDistance(), 0.01);
    }

    @Test
    public void testDuration(){
        directionsStep.setDuration(531.89);
        double expected = 531.89;
        assertEquals(expected, directionsStep.getDuration(), 0.01);
    }

    @Test
    public void testInstuction(){
        directionsStep.setInstruction("TESTINGINSTUCTION");
        String expected = "TESTINGINSTUCTION";
        assertEquals(expected, directionsStep.getInstruction());
    }

    @Test
    public void testName(){
        directionsStep.setName("TESTINGNAME");
        String expected = "TESTINGNAME";
        assertEquals(expected, directionsStep.getName());
    }

    @Test
    public void testWay_Points(){
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(56);
        expected.add(1123);
        expected.add(23);
        expected.add(73);
        directionsStep.setWay_points(expected);

        assertEquals(expected, directionsStep.getWay_points());
    }

    @Test
    public void testWayPoints(){
        GeoPoint[] expected = {new GeoPoint(5.1658, 4.163), new GeoPoint(2.0896, 7.158),
                new GeoPoint(4.0168, 6.1450), new GeoPoint(7.1498, 9.1586), };
        directionsStep.setWaypoints(expected);

        assertEquals(expected, directionsStep.getWaypoints());
    }
}
