package com.a1.nextlocation.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Route implements Parcelable {


    @NonNull
    private String name;
    private String description;
    private List<Location> locations;
    private float totalDistance;
    private String imageURL;
    private int totalTime;

    public Route(@NotNull String name) {
        this.name = name;
        this.locations = new ArrayList<>();
    }

    protected Route(Parcel in) {
        this.name = in.readString();
        this.locations = in.createTypedArrayList(Location.CREATOR);
        this.totalDistance = in.readFloat();
        this.totalTime = in.readInt();
    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public double calculateTotalTimeMinutes() {
        // 5 km / h walking speed
        return ((totalDistance / 1000) / 5) * 60;
    }


    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalDistance(float totalDistance) {
        this.totalDistance = totalDistance;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeTypedList(locations);
        parcel.writeFloat(totalDistance);
        parcel.writeInt(totalTime);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocationVisited(Location location) {
        for (Location l : this.locations) {
            if (l.getName().equals(location.getName())) {
                l.setVisited(true);
                break;
            }
        }
    }
}
