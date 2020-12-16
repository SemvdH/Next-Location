package com.a1.nextlocation.data.db.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.data.db.Database;
import com.a1.nextlocation.data.db.dao.RouteDao;

import java.util.List;

public class RouteRepository {
    private RouteDao mRouteDao;
    private LiveData<List<Route>> mAllRoutes;

    public RouteRepository(Context context) {
        Database db = Database.getDatabase(context);
        mRouteDao = db.routeDao();
        mAllRoutes = mRouteDao.getAll();
    }

    public LiveData<List<Route>> getAllRoutes() {
        return mAllRoutes;
    }

    public Route getRouteByName(String name) {
        return  mRouteDao.getRouteByName(name);
    }

    public void deleteAll() {
        mRouteDao.deleteAll();
    }
}
