package com.a1.nextlocation.data;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Route {


    @NonNull
    private String name;


    private List<Location> locations;


    private float totalDistance;


    private int totalTime;

    public Route(@NotNull String name) {

        this.name = name;
        this.locations = new ArrayList<>();

    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public float getTotalDistance() {
        //TODO calculate total distance according to all locations in list
        return totalDistance;
    }


    public int getTotalTime() {
        //TODO calculate total time according to all locations in list
        return totalTime;
    }

    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

}
