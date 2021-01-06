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

/**
 * Singleton that handles all API calls
 */
public enum ApiHandler {
    INSTANCE;


    private static String TAG = ApiHandler.class.getCanonicalName();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final String BASE_URL = "https://api.openrouteservice.org/v2/directions/";
    private final String API_KEY = "5b3ce3597851110001cf6248d4eee2099f724255918adc71cc502b2a";
    private final String DIRECTIONS_MODE = "foot-walking";

    private List<DirectionsListener> listeners = new ArrayList<>();

    private OkHttpClient client = new OkHttpClient();

    /**
     * gets directions from the start location to the end location
     * @param startLocation the start location
     * @param endLocation the end location
     */
    public void getDirections(Location startLocation, Location endLocation) {
        getDirections(startLocation.getCoordinates(),endLocation.getCoordinates());
    }

    /**
     * gets directions from the start location latitude and longitude and the end location latitude and longitude
     * @param startLat the start latitude
     * @param startLong the start longitude
     * @param endLat the end latitude
     * @param endLong the end longitude
     */
    public void getDirections(double startLat, double startLong, double endLat, double endLong) {
        getDirections(startLong + "," + startLat, endLong + "," + endLat);
    }

    /**
     * Gets the directions from the start location to the end location. An example location would be "3.543543,5.7675765"
     * @param startLocation the start location represented as <i>startlat,startlong</i>
     * @param endLocation the end location represented as <i>endlat,endlong</i>
     */
    public void getDirections(String startLocation, String endLocation) {

        // build the url
        String requestUrl = BASE_URL + DIRECTIONS_MODE + "?api_key=" + API_KEY + "&start=" +startLocation + "&end=" + endLocation;

        // start a new thread to do the request, because we don't want to be networking on our main thread
        Thread t = new Thread(() -> {

            // build a request from the url
            Request request = new Request.Builder().url(requestUrl).build();

            // try to get the response
            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    String responseString = Objects.requireNonNull(response.body()).string();
                    Log.d(TAG, "getDirections: got response: " + responseString);

                    // convert the response into a result object
                    DirectionsResult result = new DirectionsResult();
                    result.parse(responseString);
                    Log.d(TAG, "getDirections: " + result.getSteps().size());

                    // notify the listeners
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

    /**
     * adds a listener for when a result of an api call is available
     * @param listener the new listener
     */
    public void addListener(DirectionsListener listener) {
        this.listeners.add(listener);
    }

    /**
     * gets directions for the given {@link Route}
     * @param route the route to get directions for
     */
    public void getDirections(Route route) {

        // add the coordinates to the list
        ArrayList<double[]> allCoords = new ArrayList<>();
        for (Location location : route.getLocations()) {
            allCoords.add(location.getCoordinatesAsDoubles());
        }

        // convert it to a json string
        String body = "{\"coordinates\":" + new Gson().toJson(allCoords) + "}";

        String requestUrl = BASE_URL + DIRECTIONS_MODE + "?api_key=" + API_KEY;

        // new thread, because we don't want to be networking on our main thread
        Thread t = new Thread(() -> {

            // add the body to the request
            RequestBody requestBody = RequestBody.create(body,JSON);
            Request request = new Request.Builder()
                    .url(requestUrl)
                    .post(requestBody)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    String responseString = Objects.requireNonNull(response.body()).string();
                    Log.d(TAG, "getDirections: got response: " + responseString);

                    // kinda lazy, but we had time pressure
                    if (responseString.startsWith("{\"error")) {
                        Log.e(TAG, "getDirections: ERROR IN REQUEST!");
                        return;
                    }

                    // convert the result into a DirectionsResult
                    DirectionsResult result = new DirectionsResult();
                    result.parseRoute(responseString);
                    Log.d(TAG, "getDirections: " + result.getSteps().size());

                    //notify the listeners
                    for (DirectionsListener listener : listeners) {
                        listener.onDirectionsAvailable(result);
                    }
                }

            } catch (IOException e) {
                Log.d(TAG, "getDirections: caught exception: " + e.getLocalizedMessage());
            }

        });

        t.start();

//
//        try {
//            t.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }




}
