package com.a1.nextlocation;

import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RouteTest {
    private Route route;

    @Before
    public void init(){
        route = new Route("testName");
    }

    @Test
    public void nameTest(){
        String testName = "secondTestName";

        String expectedBefore = "testName";
        String expectedAfter = "secondTestName";

        assertEquals(expectedBefore, route.getName());
        route.setName(testName);
        assertEquals(expectedAfter, route.getName());
    }

    @Test
    public void getLocationsTest(){
        List<Location> expected = new ArrayList<>();

        assertEquals(expected, route.getLocations());
    }

    @Test
    public void SetLocationsTest(){
        List<Location> testList = new ArrayList<>();
        testList.add(new Location("name1", "coord1", "desc1", null));
        testList.add(new Location("name2", "coord2", "desc2", null));
        testList.add(new Location("name3", "coord3", "desc3", null));

        List<Location> expectedBefore = new ArrayList<>();
        List<Location> expectedAfter = testList;

        assertEquals(expectedBefore, route.getLocations());
        route.setLocations(testList);
        assertEquals(expectedAfter, route.getLocations());
    }

    @Test
    public void AddLocationTest(){
        Location testLocation = new Location("testLocationName", "testCoordinates", "testDescription", null);

        List<Location> expectedBefore = new ArrayList<>();
        List<Location> expectedAfter = new ArrayList<>();
        expectedAfter.add(testLocation);

        assertEquals(expectedBefore, route.getLocations());
        route.addLocation(testLocation);
        assertEquals(expectedAfter, route.getLocations());
    }

    @Test
    public void totalDistanceTest(){
        float testDistance = 523;

        float expectedBefore = 0;
        float expectedAfter = 523;

        assertEquals(expectedBefore, route.getTotalDistance(), 0.01);
        route.setTotalDistance(testDistance);
        assertEquals(expectedAfter, route.getTotalDistance(), 0.01);
    }

    @Test
    public void totalTimeTest(){
        int testTime = 36;

        int expectedBefore = 0;
        int expectedAfter = 36;

        assertEquals(expectedBefore, route.getTotalTime());
        route.setTotalTime(testTime);
        assertEquals(expectedAfter, route.getTotalTime());
    }

    @Test
    public void testDescription(){
        route.setDescription("TEST");
        String expected = "TEST";
        assertEquals(expected, route.getDescription());
        route.setDescription("FALSETEST");
        assertNotEquals(expected, route.getDescription());
    }

}
