package com.a1.nextlocation;

import androidx.test.espresso.Root;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.a1.nextlocation.fragments.CouponFragment;
import com.a1.nextlocation.fragments.LocationFragment;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class CouponFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickBackButton() throws Exception{
        //Here we click the back button and then we check if the statisticsFragment is shown
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new CouponFragment()).commit();
        onView(withId(R.id.coupon_back_button)).perform(click());
        onView(withId(R.id.statisticsFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void clickDetailButton() throws Exception{
        //Here we click a coupon and then a popup dialog shows, we press the "activeren" button in it and if the next dialog with the code and with
        //the button "Klaar" is shown then the test works
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new CouponFragment()).commit();
        onView(withId(R.id.coupon_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.string.activate)).inRoot(RootMatchers.isDialog()).perform(click());
        onView(withId(R.string.done)).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));


    }

}
