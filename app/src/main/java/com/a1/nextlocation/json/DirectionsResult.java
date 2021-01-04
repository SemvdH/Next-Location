package com.a1.nextlocation.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class DirectionsResult {
    private List<DirectionsStep> steps = new ArrayList<>();
    private double distance;
    private double duration;

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
        JsonObject features = JsonParser.parseString(json).getAsJsonObject().get("features").getAsJsonArray().get(0).getAsJsonObject();
        JsonObject segment = features.get("properties").getAsJsonObject().getAsJsonArray("segments").get(0).getAsJsonObject();

        setDistance(segment.get("distance").getAsDouble());
        setDuration(segment.get("duration").getAsDouble());

        JsonArray steps = segment.getAsJsonArray("steps");

        for (JsonElement j : steps) {
            DirectionsStep step = gson.fromJson(j,DirectionsStep.class);
            addStep(step);
        }
    }
}
