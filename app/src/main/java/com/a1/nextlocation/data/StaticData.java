package com.a1.nextlocation.data;

import java.util.ArrayList;

/**
 * singleton to keep track of different global data
 */
public enum StaticData {
    INSTANCE;
    private double distanceTraveled = 0;
    private int locationsVisited = 0;
    private long timeWalked = 0;

    private ArrayList<String> visitedNames = new ArrayList<>();

    public void addDistance(double d) {
        distanceTraveled += d;
    }

    public long getTimeWalked() {
        return timeWalked;
    }

    public void addTimeWalked(long time) {
        timeWalked += time;
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
