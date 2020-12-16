package com.a1.nextlocation.data.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.a1.nextlocation.data.Data;

@Dao
public interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Data... datas);

    @Update
    void update(Data data);

    @Query("DELETE FROM userdata")
    void delete();

    @Query("SELECT * FROM userdata LIMIT 1")
    Data getData();



}
