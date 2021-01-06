package com.a1.nextlocation.data;

import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

/**
 * singleton to keep track of different global data
 */
public enum StaticData {
    INSTANCE;
    private double distanceTraveled = 0;
    private int locationsVisited = 0;
    private boolean isFollowingRoute = false;
    private String routeName = "";

    public void followRoute(Route route) {
        routeName = route.getName();
        setFollowingRoute(true);
    }

    public boolean isFollowingRoute(Route route) {
        return isFollowingRoute && route.getName().equals(routeName);
    }
    public void setFollowingRoute(boolean followingRoute) {
        isFollowingRoute = followingRoute;
    }

    public boolean isFollowingRoute() {
        return isFollowingRoute;
    }

    private ArrayList<String> visitedNames = new ArrayList<>();

    public void addDistance(double d) {
        distanceTraveled += d;
    }


    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    public void visitLocation(Location location) {
        if (!visitedNames.contains(location.getName())) {
            locationsVisited++;
            visitedNames.add(location.getName());
        }
    }

    public int getLocationsVisited() {
        return locationsVisited;
    }

    private Polyline currentRoute;

    public void setCurrentRoute(Polyline currentRoute) {
        this.currentRoute = currentRoute;
    }

    public Polyline getCurrentRoute() {
        return currentRoute;
    }

}
