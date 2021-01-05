package com.a1.nextlocation;

import androidx.test.rule.ActivityTestRule;

import com.a1.nextlocation.fragments.CouponFragment;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LocationDetailFragment {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickBackButton() throws Exception{
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new com.a1.nextlocation.fragments.LocationDetailFragment()).commit();
        onView(withId(R.id.detail_location_back_button)).perform(click());
        onView(withId(R.id.locationFragment)).check(matches(isDisplayed()));
    }
}
