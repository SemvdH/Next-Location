package com.a1.nextlocation.json;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

/**
 * pojo class that holds the step object from the api response
 */
public class DirectionsStep {
    private double distance;
    private double duration;
    private String instruction;
    private String name;
    /**
     * these are the actual waypoints that the step refers to. The first is the beginning of the step, and the second is what it leads to.
     * The second geopoint is always the first geopoint of the next step in the list of the {@link DirectionsResult} object.
     */
    private GeoPoint[] waypoints = new GeoPoint[2];
    /**
     * this is a list of the waypoints that are in the response, it is called way_points so it can be automatically serialized with gson
     */
    private ArrayList<Integer> way_points;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getWay_points() {
        return way_points;
    }

    public void setWay_points(ArrayList<Integer> way_points) {
        this.way_points = way_points;
    }

    public GeoPoint[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(GeoPoint[] waypoints) {
        this.waypoints = waypoints;
    }
}
