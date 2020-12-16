package com.a1.nextlocation.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.a1.nextlocation.data.Coupon;

import java.util.List;

@Dao
public interface CouponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Coupon... coupons);

    @Query("DELETE FROM coupon")
    void deleteAll();

    @Query("SELECT * FROM coupon")
    LiveData<List<Coupon>> selectAll();

    @Update
    public void update(Coupon coupon);

    /*
    to add an observer to the livedata, you can use the example from https://medium.com/mindorks/using-room-database-with-livedata-android-jetpack-cbf89b677b47
     */

    @Query("SELECT * FROM coupon WHERE code = :code LIMIT 1")
    Coupon selectCouponByCode(String code);
}
