package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.FileIO;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouteLoader implements Loader<List<Route>> {
    private final Context context;

    public RouteLoader(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<Route> load() {

        FileIO<ArrayList<Route>> fileIO = new FileIO<>();

        String selectedLanguage = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("Language", "nl");
        String fileName = "routes";
        // choose the routes.json based of the selected language
        if (!selectedLanguage.equals("en")) {
            fileName += "-" + selectedLanguage;
        }

        ArrayList<Route> res = fileIO.readFileData(context, fileName + ".json",new TypeToken<ArrayList<Route>>(){}.getType());
        return res == null ? new ArrayList<>() : res;

    }
}
