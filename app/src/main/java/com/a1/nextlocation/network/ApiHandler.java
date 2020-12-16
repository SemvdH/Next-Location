package com.a1.nextlocation.network;

import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public enum ApiHandler {
    INSTANCE;


    private static String TAG = ApiHandler.class.getCanonicalName();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public Route getDirections(Location startLocation, Location endLocation) {
        return null;
    }




}
