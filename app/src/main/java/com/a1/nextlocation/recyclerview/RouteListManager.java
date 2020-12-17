package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.Route;

import java.util.List;

public class RouteListManager{

    private List<Route> routeList;
    private Context context;

    public RouteListManager(Context context){
        this.context = context;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public Route getRoute(int place) {
        return routeList.get(place);
    }

    public void load() {
        RouteLoader routeLoader = new RouteLoader(this.context);
        this.routeList = routeLoader.load();
    }
}
