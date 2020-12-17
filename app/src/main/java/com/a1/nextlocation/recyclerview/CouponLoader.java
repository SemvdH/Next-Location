package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.FileIO;
import com.a1.nextlocation.data.Location;

import java.util.List;

public class CouponLoader implements Loader<List<Coupon>> {
    private final Context context;
    public CouponLoader(Context context) {
        this.context = context;
    }
    @Override
    public List<Coupon> load() {
        FileIO<List<Coupon>> fileIO = new FileIO<>();
        try {
            return fileIO.readFileData(context,"coupons.json").newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
