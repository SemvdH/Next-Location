package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.Location;

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

    /**
     * prepares the array list for the adapter
     */
    public void load() {
        LocationLoader locationLoader = new LocationLoader(this.context);
        this.locationList = locationLoader.load();
    }

    public Location findByName(String name) {
        Location res = null;
        for (Location location : this.locationList) {
            if (location.getName().equals(name)) res = location;
            break;
        }
        return res;
    }

}
