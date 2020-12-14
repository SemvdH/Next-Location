package com.a1.nextlocation.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "coupon")
public class Coupon {

    /**
     * fields need to be public for the database to be able to use them
     */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "code")
    private String code;

    @ColumnInfo(name = "reward")
    @NonNull
    private String reward;


    public Coupon(@NonNull String code, @NotNull String reward) {
        this.code = code;
        this.reward = reward;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getReward() {
        return reward;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public void setReward(@NonNull String reward) {
        this.reward = reward;
    }
}
