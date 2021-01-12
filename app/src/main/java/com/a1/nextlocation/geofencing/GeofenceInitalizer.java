package com.a1.nextlocation.geofencing;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.a1.nextlocation.data.Data;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.recyclerview.LocationListManager;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.util.GeoPoint;

import java.util.List;

public class GeofenceInitalizer {
    private GeofencingClient geofencingClient;
    private GeoFencingHelper geoFencingHelper;
    private final Context context;
    private final String TAG = GeofenceInitalizer.class.getCanonicalName();
    private List<Location> locations;

    public GeofenceInitalizer(Context context) {
        this.context = context;
    }

    public void init(List<Location> locations) {
        geofencingClient = LocationServices.getGeofencingClient(context);
        geoFencingHelper = new GeoFencingHelper(context);
        this.locations = locations;
        addFences();
    }

    private void addFences() {
        for (Location location : locations) {
            GeoPoint t = new GeoPoint(location.getLat(), location.getLong());
            addGeofence(t, location.getName());
        }
    }

    private void addGeofence(GeoPoint p, String name) {
        Geofence geofence = geoFencingHelper.getGeofence(name, p, 30);
        GeofencingRequest geofencingRequest = geoFencingHelper.getGeoFencingRequest(geofence);
        PendingIntent pendingIntent = geoFencingHelper.getPendingIntent();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        geofencingClient.addGeofences(geofencingRequest, pendingIntent).addOnSuccessListener(v -> {
            Log.d(TAG, "addGeofence: added geofence");
        }).addOnFailureListener(v -> {

        });
    }
}
