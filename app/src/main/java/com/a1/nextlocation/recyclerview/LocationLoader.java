package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.FileIO;
import com.a1.nextlocation.data.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationLoader implements Loader<List<Location>> {
    private final Context context;

    public LocationLoader(Context context) {
        this.context = context;
    }

    @Override
    public List<Location> load() {
        FileIO<ArrayList<Location>> fileIO = new FileIO<>();

        ArrayList<Location> res = fileIO.readFileData(context, "locations.json");
        return res == null ? new ArrayList<>() : res;

    }
}
