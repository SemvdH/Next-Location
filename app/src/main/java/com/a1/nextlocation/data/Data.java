package com.a1.nextlocation.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.a1.nextlocation.data.db.CouponListTypeConverter;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "userdata")
public class Data {



    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "distance_traveled")
    private float distanceTraveled;

    @ColumnInfo(name = "locations_visited")
    private int locationsVisited;

    @ColumnInfo(name = "total_time")
    private int totalTime;

    @TypeConverters(CouponListTypeConverter.class)
    private List<Coupon> couponList;

    @Ignore
    private Location nextLocation;

    @Ignore
    private Location lastLocation;

    @Ignore
    private Route currentRoute;


    public Data() {
        this.distanceTraveled = 0;
        this.locationsVisited = 0;
        this.totalTime = 0;
        couponList = new ArrayList<>();
    }
    

    public float getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(float distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public int getLocationsVisited() {
        return locationsVisited;
    }

    public void setLocationsVisited(int locationsVisited) {
        this.locationsVisited = locationsVisited;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public Location getNextLocation() {
        return nextLocation;
    }

    public void setNextLocation(Location nextLocation) {
        this.nextLocation = nextLocation;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public Route getCurrentRoute() {
        return currentRoute;
    }

    public void setCurrentRoute(Route currentRoute) {
        this.currentRoute = currentRoute;
    }
}
