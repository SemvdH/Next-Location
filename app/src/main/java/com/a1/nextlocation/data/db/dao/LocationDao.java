package com.a1.nextlocation.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.a1.nextlocation.data.Location;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Location... locations);

    @Query("DELETE FROM location")
    void deleteAll();

    @Query("SELECT * FROM location")
    LiveData<List<Location>> selectAll();

    @Query("SELECT * FROM location WHERE name = :name LIMIT 1")
    Location getLocationByName(String name);

}
