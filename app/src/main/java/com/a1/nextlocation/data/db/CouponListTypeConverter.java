package com.a1.nextlocation.data.db;

import androidx.room.TypeConverter;

import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.Route;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CouponListTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Coupon> toRoutesList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Coupon>>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String fromRoutesList(List<Coupon> list) {
        return gson.toJson(list);
    }


}
