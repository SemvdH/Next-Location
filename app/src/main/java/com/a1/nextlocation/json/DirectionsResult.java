package com.a1.nextlocation.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.osmdroid.util.GeoPoint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DirectionsResult {
    private static final String TAG = DirectionsResult.class.getCanonicalName();
    private List<DirectionsStep> steps = new ArrayList<>();
    private double distance;
    private double duration;
    private double[][] wayPointCoordinates;

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

    public void parse(String json) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject feature = JsonParser.parseString(json).getAsJsonObject().get("features").getAsJsonArray().get(0).getAsJsonObject();
        JsonObject properties = feature.get("properties").getAsJsonObject();
        JsonArray wayPointCoordinates = feature.get("geometry").getAsJsonObject().getAsJsonArray("coordinates");
        this.wayPointCoordinates = new double[wayPointCoordinates.size()][2];


        for (int i = 0; i < wayPointCoordinates.size(); i++) {
            JsonElement j = wayPointCoordinates.get(i);
            JsonArray arr = j.getAsJsonArray();
            this.wayPointCoordinates[i][0] = arr.get(0).getAsDouble();
            this.wayPointCoordinates[i][1] = arr.get(1).getAsDouble();
        }


        JsonObject segment = properties.getAsJsonArray("segments").get(0).getAsJsonObject();

        setDistance(segment.get("distance").getAsDouble());
        setDuration(segment.get("duration").getAsDouble());

        JsonArray steps = segment.getAsJsonArray("steps");

        for (JsonElement j : steps) {
            DirectionsStep step = gson.fromJson(j,DirectionsStep.class);
            double lat;
            double longl;
            for (int i = 0; i < 2; i++) {
                lat = this.wayPointCoordinates[step.getWay_points().get(i)][0];
                longl = this.wayPointCoordinates[step.getWay_points().get(i)][1];
                step.getWaypoints()[i] = new GeoPoint(lat,longl);
            }

            addStep(step);
            Log.d(TAG, "parse: added step" + step);
        }


    }
}
