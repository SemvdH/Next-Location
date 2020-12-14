package com.a1.nextlocation.recyclerview;

import com.a1.nextlocation.data.Location;

import java.util.List;

public class LocationListManager {

    private List<Location> location;

    public LocationListManager(){

    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }
}
