package com.a1.nextlocation;

import android.graphics.Point;

import com.a1.nextlocation.data.Location;

import org.junit.Before;
import org.junit.Test;
import org.osmdroid.util.GeoPoint;

import static org.junit.Assert.*;

public class LocationTest {
    private Location testLocation;
    @Before
    public void init(){
        testLocation = new Location("testName", "15.4,27.5", "testDesc", null);
    }

    @Test
    public void nameTest(){
        String testName = "nameTestName";

        String expectedBefore = "testName";
        String expectedAfter = "nameTestName";

        assertEquals(expectedBefore, testLocation.getName());
        testLocation.setName(testName);
        assertEquals(expectedAfter, testLocation.getName());
    }

    @Test
    public void coordinatesTest(){
        String testCoordinaates = "32.4,15.7";

        String expectedBefore = "15.4,27.5";
        String expectedAfter = "32.4,15.7";

        assertEquals(expectedBefore, testLocation.getCoordinates());
        testLocation.setCoordinates(testCoordinaates);
        assertEquals(expectedAfter, testLocation.getCoordinates());
    }

    @Test
    public void descriptionTest(){
        String testDescription = "This description is a test!";

        String expectedBefore = "testDesc";
        String expectedAfter = "This description is a test!";

        assertEquals(expectedBefore, testLocation.getDescription());
        testLocation.setDescription(testDescription);
        assertEquals(expectedAfter, testLocation.getDescription());
    }

    @Test
    public void ImageURLTest(){
        String testURL = "https://i.imgur.com/FvohwaS.png";

        String expectedBefore = null;
        String expectedAfter = "https://i.imgur.com/FvohwaS.png";

        assertEquals(expectedBefore ,testLocation.getImageUrl());
        testLocation.setImageUrl(testURL);
        assertEquals(expectedAfter, testLocation.getImageUrl());
    }

    @Test
    public void coordinateDoublesTest(){
        double[] testDoubles = new double[2];
        testDoubles[0] = 15.4;
        testDoubles[1] = 27.5;

        double [] expectedCoordAsDouble = testDoubles;
        String expectedStringFromDouble = "15.4,27.5";

        assertArrayEquals(expectedCoordAsDouble, testLocation.getCoordinatesAsDoubles(), 0.1);
        assertEquals(expectedStringFromDouble, testLocation.getStringFromCoordinates(testDoubles[0], testDoubles[1]));
    }

    @Test
    public void geoPointTest(){
        String testGeoPointCoords = "30.3,55.5";

        GeoPoint expectedBefore = new GeoPoint(15.4, 27.5);
        GeoPoint expectedAfter = new GeoPoint(30.3, 55.5);

        assertEquals(expectedBefore, testLocation.convertToGeoPoint());
        testLocation.setCoordinates(testGeoPointCoords);
        assertEquals(expectedAfter, testLocation.convertToGeoPoint());
    }

    @Test
    public void AlternateConstructorTest(){
        Location alternateTestLocation = new Location("testName", 15.4, 27.5, "testDesc", null);

        assertNotNull(alternateTestLocation);
    }

    @Test
    public void calculatingDistance(){

         double expected2 = 1054.66;
         assertEquals(expected2, Location.getDistance(51.578810244278344,  4.804990650154528, 51.57696163247576, 4.789331956851824), 100);


    }
}
