package com.a1.nextlocation.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "location")
public class Location {

    @PrimaryKey
    @NonNull
    private String name;

    /**
     * coordinates will be saved as for example: 2.434343,4.65656;3.656565,6.43434
     * so lat1,long1;lat2,long2
     */
    private String coordinates;
    private String description;

    @ColumnInfo(name = "image_url")
    @Nullable
    private String imageUrl;

    public Location(@NotNull String name, String coordinates, String description, @Nullable String imageUrl) {
        this.name = name;
        this.coordinates = coordinates;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Location(@NotNull String name, double startLat, double startLong, double endLat, double endLong, String description, @Nullable String imageUrl) {
        this(name,getStringFromCoordinates(startLat,startLong,endLat,endLong),description,imageUrl);
    }

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
        double[] res = new double[4];
        String[] locations = this.coordinates.split(";");
        res[0] =Double.parseDouble(locations[0].split(",")[0]);
        res[1] = Double.parseDouble(locations[0].split(",")[1]);
        res[2] = Double.parseDouble(locations[1].split(",")[0]);
        res[3] = Double.parseDouble(locations[1].split(",")[1]);

        return res;
    }

    public static String getStringFromCoordinates(double lat1, double long1, double lat2, double long2) {
        return lat1 + "," + long1 + ";" + lat2 + "," + long2;
    }

}
