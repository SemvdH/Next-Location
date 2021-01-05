package com.a1.nextlocation.data;

import org.osmdroid.views.overlay.Polyline;

public enum CurrentRoute {
    INSTANCE;

    private Polyline currentRoute;

    public void setCurrentRoute(Polyline currentRoute) {
        this.currentRoute = currentRoute;
    }

    public Polyline getCurrentRoute() {
        return currentRoute;
    }

}
