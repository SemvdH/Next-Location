package com.a1.nextlocation.recyclerview;

import android.content.Context;

import com.a1.nextlocation.data.Coupon;

import java.util.ArrayList;
import java.util.List;

public enum CouponListManager {
    INSTANCE;

    private List<Coupon> couponList;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
        this.couponList = new ArrayList<>();
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    /**
     * prepares the list for the adapter
     */
    public void load() {
        CouponLoader couponLoader = new CouponLoader(this.context);
        this.couponList = couponLoader.load();
    }

}
