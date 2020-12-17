package com.a1.nextlocation.data;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;


public class Coupon {

    /**
     * fields need to be public for the database to be able to use them
     */
    @NonNull
    private String code;
    
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
