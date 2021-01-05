package com.a1.nextlocation.data;

import org.osmdroid.views.overlay.Polyline;

/**
 * singleton to keep track of different global data
 */
public enum StaticData {
    INSTANCE;
    private double distanceTraveled = 0;

    public void addDistance(double d) {
        distanceTraveled += d;
    }

    public double getDistanceTraveled() {
        return distanceTraveled;
    }

    private Polyline currentRoute;

    public void setCurrentRoute(Polyline currentRoute) {
        this.currentRoute = currentRoute;
    }

    public Polyline getCurrentRoute() {
        return currentRoute;
    }

}
