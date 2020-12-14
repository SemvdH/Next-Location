package com.a1.nextlocation.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.data.db.dao.CouponDao;
import com.a1.nextlocation.data.db.dao.LocationDao;
import com.a1.nextlocation.data.db.dao.RouteDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Sem
 */
@androidx.room.Database(entities = {Coupon.class,Route.class, Location.class},version = 1,exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract RouteDao routeDao();
    public abstract CouponDao couponDao();
    public abstract LocationDao locationDao();

    private static volatile Database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (Database.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class,"next_location_db").addCallback(callback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriterExecutor.execute(() -> {
                // TODO populate our database here
            });

        }
    };
}
