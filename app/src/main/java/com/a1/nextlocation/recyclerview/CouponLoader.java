package com.a1.nextlocation.recyclerview;

import android.content.Context;
import android.util.Log;

import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.FileIO;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class CouponLoader implements Loader<List<Coupon>> {
    private final Context context;
    private final String TAG = CouponLoader.class.getCanonicalName();

    public CouponLoader(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<Coupon> load() {
        FileIO<ArrayList<Coupon>> fileIO = new FileIO<>();

        String selectedLanguage = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("Language", "nl");
        String fileName = "locations";
        // choose the coupon.json based of the selected language
        if (!selectedLanguage.equals("en")) {
            fileName += "-" + selectedLanguage;
        }
        ArrayList<Coupon> res = fileIO.readFileData(context, fileName + ".json", new TypeToken<ArrayList<Coupon>>(){}.getType());
        Log.d(TAG, "load: " + res);

        return res == null ? new ArrayList<>() : res;

    }
}
