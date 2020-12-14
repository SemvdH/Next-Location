package com.a1.nextlocation.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.a1.nextlocation.data.Route;

import java.util.List;

@Dao
public interface RouteDao {

    @Insert
    void insertAll(Route... routes);

    @Query("DELETE FROM route")
    void deleteAll();

    @Query("SELECT * FROM route")
    LiveData<List<Route>> getAll();

    @Query("SELECT * FROM route where name = :name LIMIT 1")
    Route getRouteByName(String name);
}
