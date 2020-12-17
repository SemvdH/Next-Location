package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.FileIO;
import com.a1.nextlocation.data.Location;

import java.util.List;

public class LocationLoader implements Loader<List<Location>>{
    private final Context context;

    public LocationLoader(Context context) {
        this.context = context;
    }

    @Override
    public List<Location> load() {
        FileIO<List<Location>> fileIO = new FileIO<>();
        try {
            return fileIO.readFileData(context,"locations.json").newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
