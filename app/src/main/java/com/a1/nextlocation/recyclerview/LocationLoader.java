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

    /**
     * loads the array list from a JSON file
     * @return array list with locations
     */
    @Override
    public ArrayList<Location> load() {
        FileIO<ArrayList<Location>> fileIO = new FileIO<>();

        String selectedLanguage = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("Language", "nl");
        String fileName = "locations";
        // choose the locations.json based of the selected language
        if (!selectedLanguage.equals("en")) {
            fileName += "-" + selectedLanguage;
        }

        ArrayList<Location> res = fileIO.readFileData(context,fileName + ".json",new TypeToken<ArrayList<Location>>(){}.getType());

        return res == null ? new ArrayList<>() : res;
    }
}
