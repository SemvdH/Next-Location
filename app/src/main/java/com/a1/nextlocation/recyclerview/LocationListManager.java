package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.Location;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public enum LocationListManager {
    INSTANCE;

    private List<Location> locationList;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
        this.locationList = new ArrayList<>();
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public Location getLocation(int place) {
        return locationList.get(place);
    }

    public void load() {
        LocationLoader locationLoader = new LocationLoader(this.context);
        this.locationList = locationLoader.load();
    }

}
