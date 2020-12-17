package com.a1.nextlocation.recyclerview;

import com.a1.nextlocation.data.Route;

import java.util.List;

public class RouteListManager {

    List<Route> routes;

    public RouteListManager(){

    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
