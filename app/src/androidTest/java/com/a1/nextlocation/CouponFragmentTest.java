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
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new CouponFragment()).commit();
        onView(withId(R.id.couponBackButton)).perform(click());
        onView(withId(R.id.statisticsFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void clickDetailButton() throws Exception{
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new CouponFragment()).commit();
        onView(withId(R.id.couponRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withText("activeren")).inRoot(RootMatchers.isDialog()).perform(click());
        onView(withText("Klaar")).inRoot(RootMatchers.isDialog()).check(matches(isDisplayed()));


    }

}
