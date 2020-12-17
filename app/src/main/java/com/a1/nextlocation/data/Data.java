package com.a1.nextlocation.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private float distanceTraveled;

    private int locationsVisited;

    private int totalTime;

    private List<Coupon> couponList;

    private Location nextLocation;

    private Location lastLocation;

    private Route currentRoute;


    public Data() {
        this.distanceTraveled = 0;
        this.locationsVisited = 0;
        this.totalTime = 0;
        couponList = new ArrayList<>();
    }
    

    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(float distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public int getLocationsVisited() {
        return locationsVisited;
    }

    public void setLocationsVisited(int locationsVisited) {
        this.locationsVisited = locationsVisited;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(Location nextLocation) {
        this.nextLocation = nextLocation;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public Route getCurrentRoute() {
        return currentRoute;
    }

    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }
}
