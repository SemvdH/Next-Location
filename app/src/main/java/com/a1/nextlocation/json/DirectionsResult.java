package com.a1.nextlocation.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import org.json.JSONArray;
import org.osmdroid.util.GeoPoint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectionsResult {
    private static final String TAG = DirectionsResult.class.getCanonicalName();
    private List<DirectionsStep> steps = new ArrayList<>();
    private double distance;
    private double duration;
    private double[][] wayPointCoordinates;
    private GeoPoint[] startAndEndPoint = new GeoPoint[2];

    public List<DirectionsStep> getSteps() {
        return steps;
    }

    public void setSteps(List<DirectionsStep> steps) {
        this.steps = steps;
    }

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

    public void addStep(DirectionsStep step) {
        this.steps.add(step);
    }

    public GeoPoint[] getStartAndEndPoint() {
        return startAndEndPoint;
    }

    /**
     * converts all the geopoints in all the steps into an arraylist to display it on the map
     * @return the list of geopoints
     */
    public ArrayList<GeoPoint> getGeoPoints() {
        int size = 0;
        // we'll have a lot of waypoints, so calculate the size first so that the list won't have to be extended (o p t i m i z e)
        for (int i = 0; i < this.getSteps().size(); i++) {
            size += this.getSteps().get(i).getWaypoints().length;
        }

        ArrayList<GeoPoint> res = new ArrayList<>(size);
        for (DirectionsStep step : this.getSteps()) {
            Collections.addAll(res, step.getWaypoints());
        }
        return res;
    }

    /**
     * parses a given json string into this object. It gets all the waypoints and steps and combines them so that every step also has the correct coordinates associated with it
     *
     * @param json the json string to parse.
     */
    public void parse(String json) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject feature = JsonParser.parseString(json).getAsJsonObject().get("features").getAsJsonArray().get(0).getAsJsonObject();
        JsonObject properties = feature.get("properties").getAsJsonObject();
        JsonArray wayPointCoordinates = feature.get("geometry").getAsJsonObject().getAsJsonArray("coordinates");
        this.wayPointCoordinates = new double[wayPointCoordinates.size()][2];


        // fill the way point coordinates list for later use
        for (int i = 0; i < wayPointCoordinates.size(); i++) {
            JsonElement j = wayPointCoordinates.get(i);
            JsonArray arr = j.getAsJsonArray();
            this.wayPointCoordinates[i][0] = arr.get(0).getAsDouble();
            this.wayPointCoordinates[i][1] = arr.get(1).getAsDouble();
        }


        JsonArray segments = properties.getAsJsonArray("segments");

        for (JsonElement element : segments) {
            JsonObject segment = element.getAsJsonObject();

            setDistance(segment.get("distance").getAsDouble());
            setDuration(segment.get("duration").getAsDouble());

            JsonArray steps = segment.getAsJsonArray("steps");

            for (JsonElement j : steps) {

                DirectionsStep step = gson.fromJson(j, DirectionsStep.class);
                double lat;
                double longl;

                // kinda stinky but it works
                for (int i = 0; i < 2; i++) {
                    lat = this.wayPointCoordinates[step.getWay_points().get(i)][0];
                    longl = this.wayPointCoordinates[step.getWay_points().get(i)][1];
                    step.getWaypoints()[i] = new GeoPoint(lat, longl);
                }

                addStep(step);
                Log.d(TAG, "parse: added step" + step);
            }
        }

        startAndEndPoint[0] = this.getSteps().get(0).getWaypoints()[0];
        startAndEndPoint[1] = this.getSteps().get(this.getSteps().size()-1).getWaypoints()[1];

    }

    public void parseRoute(String json) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray routes = JsonParser.parseString(json).getAsJsonObject().getAsJsonArray("routes");
        for (JsonElement element : routes) {
            JsonObject route = element.getAsJsonObject();
            JsonObject summary = route.getAsJsonObject("summary");
            this.distance = summary.get("distance").getAsDouble();
            this.duration = summary.get("duration").getAsDouble();

            JsonPrimitive geometry = route.getAsJsonPrimitive("geometry");
            JsonArray wayPointCoordinates = GeometryDecoder.decodeGeometry(geometry.getAsString(), false);
            this.wayPointCoordinates = new double[wayPointCoordinates.size()][2];


            // fill the way point coordinates list for later use
            for (int i = 0; i < wayPointCoordinates.size(); i++) {
                JsonElement j = wayPointCoordinates.get(i);
                JsonArray arr = j.getAsJsonArray();
                this.wayPointCoordinates[i][0] = arr.get(0).getAsDouble();
                this.wayPointCoordinates[i][1] = arr.get(1).getAsDouble();
            }


            JsonArray segments = route.getAsJsonArray("segments");

            for (JsonElement e : segments) {
                JsonObject segment = e.getAsJsonObject();

                setDistance(segment.get("distance").getAsDouble());
                setDuration(segment.get("duration").getAsDouble());

                JsonArray steps = segment.getAsJsonArray("steps");

                for (JsonElement j : steps) {

                    DirectionsStep step = gson.fromJson(j, DirectionsStep.class);
                    double lat;
                    double longl;

                    // kinda stinky but it works
                    for (int i = 0; i < 2; i++) {
                        lat = this.wayPointCoordinates[step.getWay_points().get(i)][0];
                        longl = this.wayPointCoordinates[step.getWay_points().get(i)][1];
                        step.getWaypoints()[i] = new GeoPoint(lat, longl);
                    }

                    addStep(step);
                    Log.d(TAG, "parse: added step" + step);
                }
            }

        }

    }
}
