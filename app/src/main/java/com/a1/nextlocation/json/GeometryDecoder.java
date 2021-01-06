package com.a1.nextlocation.json;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * source: https://github.com/GIScience/openrouteservice-docs#geometry-decoding
 */
public class GeometryDecoder {

    public static JsonArray decodeGeometry(String encodedGeometry, boolean inclElevation) {
        JsonArray geometry = new JsonArray();
        int len = encodedGeometry.length();
        int index = 0;
        int lat = 0;
        int lng = 0;
        int ele = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedGeometry.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedGeometry.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);


            if(inclElevation){
                result = 1;
                shift = 0;
                do {
                    b = encodedGeometry.charAt(index++) - 63 - 1;
                    result += b << shift;
                    shift += 5;
                } while (b >= 0x1f);
                ele += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);
            }

            JsonArray location = new JsonArray();
            location.add(lat / 1E5);
            location.add(lng / 1E5);
            if(inclElevation){
                location.add((float) (ele / 100));
            }
            geometry.add(location);
        }
        return geometry;
    }
}