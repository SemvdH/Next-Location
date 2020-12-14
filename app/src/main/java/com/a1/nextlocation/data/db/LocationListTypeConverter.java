package com.a1.nextlocation.data.db;

import androidx.room.TypeConverter;

import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class LocationListTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Location> toLocationList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Location>>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String fromLocationList(List<Location> list) {
        return gson.toJson(list);
    }
}
