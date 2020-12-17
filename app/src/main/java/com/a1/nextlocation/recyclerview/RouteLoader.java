package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.FileIO;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;

import java.util.List;

public class RouteLoader implements Loader<List<Route>> {
    private final Context context;
    public RouteLoader(Context context) {
        this.context = context;
    }

    @Override
    public List<Route> load() {

        FileIO<List<Route>> fileIO = new FileIO<>();
        try {
            return fileIO.readFileData(context,"routes.json").newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
