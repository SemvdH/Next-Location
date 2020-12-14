package com.a1.nextlocation.data;

import androidx.annotation.NonNull;
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

    public Location(@NotNull String name, String coordinates, String description) {
        this.name = name;
        this.coordinates = coordinates;
        this.description = description;
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
}
