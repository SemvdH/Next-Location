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

}
