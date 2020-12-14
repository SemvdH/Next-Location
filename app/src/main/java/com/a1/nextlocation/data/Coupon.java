package com.a1.nextlocation.data;

public class Coupon {

    private String code;
    private String reward;


    public Coupon(String code, String reward) {
        this.code = code;
        this.reward = reward;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
}
