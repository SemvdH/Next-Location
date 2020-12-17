package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.FileIO;
import com.a1.nextlocation.data.Location;

import java.util.ArrayList;
import java.util.List;

public class CouponLoader implements Loader<List<Coupon>> {
    private final Context context;

    public CouponLoader(Context context) {
        this.context = context;
    }

    @Override
    public List<Coupon> load() {
        FileIO<ArrayList<Coupon>> fileIO = new FileIO<>();
        ArrayList<Coupon> res = fileIO.readFileData(context, "coupons.json");
        return res == null ? new ArrayList<>() : res;
    }
}
