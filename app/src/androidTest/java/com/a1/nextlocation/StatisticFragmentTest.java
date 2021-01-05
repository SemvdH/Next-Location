package com.a1.nextlocation;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.a1.nextlocation.fragments.LocationFragment;
import com.a1.nextlocation.fragments.StatisticFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

public class StatisticFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //Tests to see if all boxes get created
    @Test
    public void checkText() throws Exception{
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new StatisticFragment()).commit();
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
        onView(withId(R.id.name_box)).check(matches(isDisplayed()));
        onView(withId(R.id.Box2)).check(matches(isDisplayed()));
        onView(withId(R.id.Box3)).check(matches(isDisplayed()));
        onView(withId(R.id.Box4)).check(matches(isDisplayed()));
    }



}
