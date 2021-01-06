package com.a1.nextlocation;

import android.app.LauncherActivity;
import android.widget.Button;

import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.rule.ActivityTestRule;

import com.a1.nextlocation.fragments.CouponFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {

    

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickLocationsNavBar() throws Exception{
        //Here we click the back button and then we check if the statisticsFragment is shown
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().commit();
        onView(withId(R.id.locations)).perform(click());
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void clickRouteNavBar() throws Exception{
        //Here we click the back button and then we check if the statisticsFragment is shown
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().commit();
        onView(withId(R.id.routes)).perform(NavigationViewActions.navigateTo(R.id.routes));
        onView(withId(R.id.routeFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void clickStatisticsNavBar() throws Exception{
        //Here we click the back button and then we check if the statisticsFragment is shown
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().commit();
        onView(withId(R.id.statistics)).perform(click());
        onView(withId(R.id.statisticsFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void clickSettingNavBar() throws Exception{
        //Here we click the back button and then we check if the statisticsFragment is shown
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().commit();
        onView(withId(R.id.settings)).perform(click());
        onView(withId(R.id.settingFragment)).check(matches(isDisplayed()));
    }

}
