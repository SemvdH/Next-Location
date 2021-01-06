package com.a1.nextlocation.network;

import android.util.Log;

import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.json.DirectionsResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public enum ApiHandler {
    INSTANCE;


    private static String TAG = ApiHandler.class.getCanonicalName();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final String BASE_URL = "https://api.openrouteservice.org/v2/directions/";
    private final String API_KEY = "5b3ce3597851110001cf6248d4eee2099f724255918adc71cc502b2a";
    private final String DIRECTIONS_MODE = "foot-walking";
    private List<DirectionsListener> listeners = new ArrayList<>();

    private OkHttpClient client = new OkHttpClient();

    public void getDirections(Location startLocation, Location endLocation) {
        getDirections(startLocation.getCoordinates(),endLocation.getCoordinates());
    }

    public void getDirections(double startLat, double startLong, double endLat, double endLong) {
        getDirections(startLong + "," + startLat, endLong + "," + endLat);
    }

    public void getDirections(String startLocation, String endLocation) {

        String requestUrl = BASE_URL + DIRECTIONS_MODE + "?api_key=" + API_KEY + "&start=" +startLocation + "&end=" + endLocation;
        Thread t = new Thread(() -> {

            Request request = new Request.Builder().url(requestUrl).build();

            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    String responseString = Objects.requireNonNull(response.body()).string();
                    Log.d(TAG, "getDirections: got response: " + responseString);

                    DirectionsResult result = new DirectionsResult();
                    result.parse(responseString);
                    Log.d(TAG, "getDirections: " + result.getSteps().size());

                    for (DirectionsListener listener : listeners) {
                        listener.onDirectionsAvailable(result);
                    }
                }

            } catch (IOException e) {
                Log.d(TAG, "getDirections: caught exception: " + e.getLocalizedMessage());
            }
        });

        t.start();

//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    public void addListener(DirectionsListener listener) {
        this.listeners.add(listener);
    }

    public void getDirections(Route route) {
//        for (int i = 0; i < route.getLocations().size()-1; i+= 2) {
//            Location start = route.getLocations().get(i);
//            Location end = route.getLocations().get(i+1);
//            getDirections(start,end);
//        }

        ArrayList<double[]> allCoords = new ArrayList<>();
        for (Location location : route.getLocations()) {
            allCoords.add(location.getCoordinatesAsDoubles());
        }

        String body = "{\"coordinates\":" + new Gson().toJson(allCoords) + "}";


        String requestUrl = BASE_URL + DIRECTIONS_MODE + "?api_key=" + API_KEY;

        Thread t = new Thread(() -> {

            RequestBody requestBody = RequestBody.create(body,JSON);
            Request request = new Request.Builder()
                    .url(requestUrl)
                    .post(requestBody)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    String responseString = Objects.requireNonNull(response.body()).string();
                    Log.d(TAG, "getDirections: got response: " + responseString);
                    if (responseString.startsWith("{\"error")) {
                        Log.e(TAG, "getDirections: ERROR IN REQUEST!");
                        return;
                    }

                    DirectionsResult result = new DirectionsResult();
                    result.parseRoute(responseString);
                    Log.d(TAG, "getDirections: " + result.getSteps().size());

                    for (DirectionsListener listener : listeners) {
                        listener.onDirectionsAvailable(result);
                    }
                }

            } catch (IOException e) {
                Log.d(TAG, "getDirections: caught exception: " + e.getLocalizedMessage());
            }
        });

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }




}
