package com.a1.nextlocation;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.a1.nextlocation.fragments.LocationFragment;
import com.a1.nextlocation.fragments.RouteFragment;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RouteFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickBackButton() throws Exception{
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new RouteFragment()).commit();
        onView(withId(R.id.route_back_button)).perform(click());
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()));

    }

    @Test
    public void clickDetailButton() throws Exception{
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new RouteFragment()).commit();
        onView(withId(R.id.route_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.routeDetailFragment)).check(matches(isDisplayed()));

    }
}
