package com.a1.nextlocation;

import com.a1.nextlocation.data.Coupon;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CouponTestClass {
    private Coupon testCoupon;

    @Before
    public void init(){
        testCoupon = new Coupon("TESTCODE", "REWARDISTRUE");
    }

    @Test
    public void testSetCode() {
        testCoupon.setCode("testing");
        String expected = "testing";
        assertEquals(expected, testCoupon.getCode());
    }

    @Test
    public void testSetReward() {
        testCoupon.setReward("testreward");
        String expected = "testreward";
        assertNotNull(testCoupon.getReward());
        assertEquals(expected, testCoupon.getReward());
    }
}
