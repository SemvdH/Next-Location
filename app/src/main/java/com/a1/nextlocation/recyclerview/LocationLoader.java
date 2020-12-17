package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.FileIO;
import com.a1.nextlocation.data.Location;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationLoader implements Loader<List<Location>> {
    private final Context context;

    public LocationLoader(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<Location> load() {
        FileIO<ArrayList<Location>> fileIO = new FileIO<>();

        ArrayList<Location> res = fileIO.readFileData(context,"locations.json",new TypeToken<ArrayList<Location>>(){}.getType());

        return res == null ? new ArrayList<>() : res;
    }
}
