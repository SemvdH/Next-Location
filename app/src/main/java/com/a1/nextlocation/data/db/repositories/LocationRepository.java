package com.a1.nextlocation.data.db.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.db.Database;
import com.a1.nextlocation.data.db.dao.LocationDao;

import java.util.List;

public class LocationRepository {
    private LocationDao mLocationDao;
    private LiveData<List<Location>> mAllLocations;

    public LocationRepository(Context context) {
        Database db = Database.getDatabase(context);
        mLocationDao = db.locationDao();
        mAllLocations = mLocationDao.selectAll();
    }

    public LiveData<List<Location>> getAllLocations() {
        return mAllLocations;
    }

    public Location getLocationByName(String name) {
        return mLocationDao.getLocationByName(name);
    }

    public void insertAll(Location... locations) {
        mLocationDao.insertAll(locations);
    }

    public void deleteAll() {
        mLocationDao.deleteAll();
    }
}
