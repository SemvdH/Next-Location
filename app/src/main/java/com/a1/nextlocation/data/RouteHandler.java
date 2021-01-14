package com.a1.nextlocation.data;

import org.osmdroid.views.overlay.Polyline;

/**
 * singleton to track the current route that is being followed
 */
public enum RouteHandler {
    INSTANCE;

    private boolean isFollowingRoute = false;
    private Route currentRoute;
    private int stepCount = 0;
    private RouteFinishedListener routeFinishedListener;
    private long startedTime;
    private double currentRouteDuration;

    public void setCurrentRouteDuration(double currentRouteDuration) {
        this.currentRouteDuration = currentRouteDuration;
    }

    public double getCurrentRouteDuration() {
        return currentRouteDuration;
    }

    private Polyline currentRouteLine;

    public void setCurrentRouteLine(Polyline currentRouteLine) {
        this.currentRouteLine = currentRouteLine;
    }


    public Polyline getCurrentRouteLine() {
        return currentRouteLine;
    }

    public void setRouteFinishedListener(RouteFinishedListener routeFinishedListener) {
        this.routeFinishedListener = routeFinishedListener;
    }

    public RouteFinishedListener getRouteFinishedListener() {
        return routeFinishedListener;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void addStep() {
        stepCount++;
    }

    public void finishRoute() {
        stepCount = 0;
        isFollowingRoute = false;
        currentRoute = null;
        currentRouteLine = null;
        Data.INSTANCE.addTimeWalked(System.currentTimeMillis() - startedTime);
        startedTime = 0;
    }

    public void followRoute(Route route) {
        if (isFollowingRoute) {
            Data.INSTANCE.addTimeWalked(System.currentTimeMillis() - startedTime);
        }
        this.currentRoute = route;

        // sync the visited locations with the route
        for (Location l : this.currentRoute.getLocations()) {
            if (Data.INSTANCE.isVisited(l)) {
                this.currentRoute.setLocationVisited(l);
            }
        }

        setFollowingRoute(true);
        startedTime = System.currentTimeMillis();
    }

    public boolean isFollowingRoute(Route route) {
        return isFollowingRoute && route.equals(currentRoute);
    }

    public void setFollowingRoute(boolean followingRoute) {
        isFollowingRoute = followingRoute;
    }

    public boolean isFollowingRoute() {
        return isFollowingRoute;
    }

    public Route getCurrentRoute() {
        return currentRoute;
    }

    @FunctionalInterface
    public interface RouteFinishedListener {
        void onRouteFinish();
    }
}
