package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.Location;

import java.util.List;

public class LocationListManager {

    private List<Location> locationList;
    private Context context;

    public LocationListManager(Context context){
        this.context = context;

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
