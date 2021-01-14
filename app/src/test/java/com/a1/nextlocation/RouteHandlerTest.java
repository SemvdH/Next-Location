package com.a1.nextlocation;

import android.util.Log;

import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.data.RouteHandler;

import org.junit.Before;
import org.junit.Test;
import org.osmdroid.views.overlay.Polyline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class RouteHandlerTest {
    private RouteHandler routeHandler;

    @Before
    public void init(){
        routeHandler = RouteHandler.INSTANCE;
    }

    @Test
    public void testRouteLine(){
        Polyline mPol = mock(Polyline.class);
        routeHandler.setCurrentRouteLine(mPol);
        assertEquals(mPol, routeHandler.getCurrentRouteLine());
    }

    @Test
    public void testStepCount(){
        int expected = 0;
        for (int i = 0; i < 100; i++) {
            routeHandler.addStep();
            expected++;
        }
        assertEquals(expected, routeHandler.getStepCount());
        expected += 10;
        assertNotEquals(expected, routeHandler.getStepCount());
    }

    @Test
    public void testRouteFollowing(){
        Route testRoute = new Route("");
        routeHandler.followRoute(testRoute);
        boolean expected = true;
        assertEquals(expected, routeHandler.isFollowingRoute(testRoute));
        assertEquals(expected, routeHandler.isFollowingRoute());
        assertEquals(testRoute, routeHandler.getCurrentRoute());

        try {
            routeHandler.finishRoute();
        } catch (NullPointerException e) {
            System.out.println("shared preferences not mocked");
        }
        assertNull(routeHandler.getCurrentRoute());

        routeHandler.followRoute(new Route("FALSEROUTENAME"));
        assertNotEquals(expected, routeHandler.isFollowingRoute(testRoute));
    }

    @Test
    public void test(){
        routeHandler.setRouteFinishedListener(() -> {
            System.out.println("TEST");
        });
        assertNotNull(routeHandler.getRouteFinishedListener());
    }
}
