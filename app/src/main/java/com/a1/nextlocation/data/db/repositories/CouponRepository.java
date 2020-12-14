package com.a1.nextlocation.data.db.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.db.dao.CouponDao;
import com.a1.nextlocation.data.db.Database;

import java.util.List;

public class CouponRepository {
    private CouponDao mCouponDao;
    private LiveData<List<Coupon>> mAllCoupons;

    public CouponRepository(Context context) {
        Database db = Database.getDatabase(context);
        mCouponDao = db.couponDao();
        mAllCoupons = mCouponDao.selectAll();
    }

    public LiveData<List<Coupon>> getAllCoupons() {
        return mAllCoupons;
    }

    public Coupon getCoupon(String code) {
        return mCouponDao.selectCouponByCode(code);
    }
}
