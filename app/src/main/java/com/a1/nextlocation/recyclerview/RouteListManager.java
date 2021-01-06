package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.Route;

import java.util.ArrayList;
import java.util.List;

public enum RouteListManager {
    INSTANCE;

    private List<Route> routeList;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
        this.routeList = new ArrayList<>();
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    /**
     * prepares the array list for the adapter
     */
    public void load() {
        RouteLoader routeLoader = new RouteLoader(this.context);
        this.routeList = routeLoader.load();
    }
}
