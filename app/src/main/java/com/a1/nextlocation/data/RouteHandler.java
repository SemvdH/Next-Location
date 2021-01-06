package com.a1.nextlocation.data;

/**
 * singleton to track the current route that is being followed
 */
public enum RouteHandler {
    INSTANCE;

    private boolean isFollowingRoute = false;
    private Route currentRoute;
    private int stepCount = 0;

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
    }

    public void followRoute(Route route) {
        this.currentRoute = route;
        setFollowingRoute(true);
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
}
