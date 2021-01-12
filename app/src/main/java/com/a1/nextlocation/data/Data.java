package com.a1.nextlocation.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    private SharedPreferences.Editor editor;
    private Context context;
    private LocationProximityListener locationProximityListener;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setEditor(SharedPreferences.Editor editor) {
        this.editor = editor;
    }


    public LocationProximityListener getLocationProximityListener() {
        return locationProximityListener;
    }

    public void setLocationProximityListener(LocationProximityListener locationProximityListener) {
        this.locationProximityListener = locationProximityListener;
    }

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    private ArrayList<String> visitedNames = new ArrayList<>();

    public void addDistance(double d) {
        distanceTraveled += d;

        editor.putString("distanceTraveled", String.valueOf(distanceTraveled));
        editor.apply();
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
            saveVisitedNamesList();
            editor.putInt("locationsVisited", locationsVisited);
            editor.apply();
        }
    }

    public int getLocationsVisited() {
        return locationsVisited;
    }

    @FunctionalInterface
    public interface LocationProximityListener {
        void onLocationVisited(Location location);
    }

    public void saveVisitedNamesList(){
        Gson gson = new Gson();
        String json = gson.toJson(visitedNames);
        editor.putString("visitedNames", json);
        editor.apply();
    }

    public ArrayList<String> loadAndGetVisitedNamesList(){
        String json = context.getSharedPreferences("Data", Context.MODE_PRIVATE).getString("visitedNames", "[]");
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        visitedNames = new Gson().fromJson(json, type);
        return visitedNames;
    }

    public void load(){
        SharedPreferences prefs = context.getSharedPreferences("Data", Context.MODE_PRIVATE);
        this.editor = prefs.edit();
        Data.INSTANCE.addDistance(Double.parseDouble(prefs.getString("distanceTraveled", "0")));
        this.locationsVisited = loadAndGetVisitedNamesList().size();
    }

}
