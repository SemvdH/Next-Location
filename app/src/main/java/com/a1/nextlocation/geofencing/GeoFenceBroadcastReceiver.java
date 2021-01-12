package com.a1.nextlocation.geofencing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.a1.nextlocation.data.Data;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.recyclerview.LocationListManager;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * broadcast receiver for geofence events
 */
public class GeoFenceBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = GeoFenceBroadcastReceiver.class.getCanonicalName();

    @Override
    public void onReceive(Context context, Intent intent) {

        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        Log.i(TAG, "onReceive: RECEIVED GEOFENCE STUFF");

        if (geofencingEvent.hasError()) {
            String errorMessage = GeofenceStatusCodes
                    .getStatusCodeString(geofencingEvent.getErrorCode());
            Log.e(TAG, errorMessage);
            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        switch (geofenceTransition) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();
                // loop through list of geofences
                for (Geofence geofence : geofenceList) {
                    for (Location l : LocationListManager.INSTANCE.getLocationList()) {
                        if (geofence.getRequestId().equals(l.getName())) {
                            l.setVisited(true);
                            // let the homefragment know that we are close to a location
                            Data.INSTANCE.getLocationProximityListener().onLocationVisited(l);
                            Log.d(TAG, "onReceive: VISITED LOCATION " + l.getName());
                            break;
                        }
                    }
                }

                break;
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Log.d(TAG, "onReceive: exiting geofence...");
                break;
        }
    }
}
