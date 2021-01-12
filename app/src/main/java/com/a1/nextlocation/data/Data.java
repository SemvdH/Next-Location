package com.a1.nextlocation.data;

import java.util.ArrayList;

/**
 * singleton to keep track of different global data
 */
public enum Data {
    INSTANCE;
    private double distanceTraveled = 0;
    private int locationsVisited = 0;
    private long totalTime = 0;
    private double zoom = 0;
    private android.location.Location location;

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    private final ArrayList<String> visitedNames = new ArrayList<>();

    public void addDistance(double d) {
        distanceTraveled += d;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void addTimeWalked(long time) {
        totalTime += time;
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


    public android.location.Location getLocation() {
        return location;
    }

    public void setLocation(android.location.Location location) {
        this.location = location;
    }
}
