package com.a1.nextlocation.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;
import org.osmdroid.util.GeoPoint;

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

    public Location(String name, android.location.Location loc, String description, String imageUrl) {
        this(name,getStringFromCoordinates(loc.getLatitude(),loc.getLongitude()),description,imageUrl);
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
        res[1] = getLat();
        res[0] = getLong();

        return res;
    }

    public double getLat() {
        return Double.parseDouble(this.coordinates.split(",")[0]);
    }

    public double getLong() {
        return Double.parseDouble(this.coordinates.split(",")[1]);
    }

    public static String getStringFromCoordinates(double lat1, double long1) {
        return long1 + "," + lat1;
    }

    /**
     * calculates the distance between two coordinates
     * @param lat1 the first latitude
     * @param lon1 the first longitude
     * @param lat2 the second latitude
     * @param lon2 the second longitude
     * @return the distance between the coordinates in meters
     */
    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
//        if(miles) {
//            double r = 3956;
//        }
//        else {
            double r = 6371;
//        }
        // calculate the result
        double distance = c * r;

        distance *= 1000;
        return Math.floor(distance);
    }

    public GeoPoint convertToGeoPoint() {
        return new GeoPoint(this.getLat(),this.getLong());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(coordinates);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
    }
}
