package com.a1.nextlocation.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

public class Location implements Parcelable {

    @NonNull
    private String name;

    /**
     * coordinates will be saved as for example: 2.434343,4.65656;3.656565,6.43434
     * so lat1,long1;lat2,long2
     */
    private String coordinates;
    private String description;

    @Nullable
    private String imageUrl;

    public Location(@NotNull String name, String coordinates, String description, @Nullable String imageUrl) {
        this.name = name;
        this.coordinates = coordinates;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Location(@NotNull String name, double latCoord, double longCoord, String description, @Nullable String imageUrl) {
        this(name,getStringFromCoordinates(latCoord,longCoord),description,imageUrl);
    }

    protected Location(Parcel in) {
        name = in.readString();
        coordinates = in.readString();
        description = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Nullable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@Nullable String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double[] getCoordinatesAsDoubles() {
        double[] res = new double[2];
        res[0] = getLat();
        res[1] = getLong();

        return res;
    }

    public double getLat() {
        return Double.parseDouble(this.coordinates.split(",")[0]);
    }

    public double getLong() {
        return Double.parseDouble(this.coordinates.split(",")[1]);
    }

    public static String getStringFromCoordinates(double lat1, double long1) {
        return lat1 + "," + long1;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(coordinates);
        dest.writeString(description);
        dest.writeString(imageUrl);
    }
}
