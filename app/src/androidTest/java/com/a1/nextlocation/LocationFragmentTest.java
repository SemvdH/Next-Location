package com.a1.nextlocation;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.a1.nextlocation.fragments.LocationFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;


public class LocationFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickBackButton() throws Exception{
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new LocationFragment()).commit();
        onView(withId(R.id.locationBackButton)).perform(click());
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()));

    }

    @Test
    public void clickDetailButton() throws Exception{
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new LocationFragment()).commit();
        onView(withId(R.id.locationRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.locationDetailFragment)).check(matches(isDisplayed()));

    }

}
