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
    private long timeWalkedRoute = 0;
    private double zoom = 0;

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    private ArrayList<String> visitedNames = new ArrayList<>();

    public void addDistance(double d) {
        distanceTraveled += d;
    }

    public long getTimeWalkedRoute() {
        return timeWalkedRoute;
    }

    public void addTimeWalked(long time) {
        timeWalkedRoute += time;
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



}
