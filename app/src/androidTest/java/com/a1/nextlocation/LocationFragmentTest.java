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
        //Here we click the back button and then we check if the homeFragment is shown
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new LocationFragment()).commit();
        onView(withId(R.id.location_back_button)).perform(click());
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()));

    }

    @Test
    public void clickDetailButton() throws Exception{
        //Here we click an item in the recyclerview and then check if the routeDetailFragment is called
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new LocationFragment()).commit();
        onView(withId(R.id.location_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.locationDetailFragment)).check(matches(isDisplayed()));

    }

}
