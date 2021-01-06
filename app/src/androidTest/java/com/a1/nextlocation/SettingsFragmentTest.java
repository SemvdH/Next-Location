package com.a1.nextlocation;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.a1.nextlocation.fragments.SettingsFragment;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class SettingsFragmentTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //Tests if the back button sends you back home
    @Test
    public void clickBackButton() throws Exception{
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new SettingsFragment()).commit();
        onView(withId(R.id.settings_back_button)).perform(click());
        onView(withId(R.id.homeFragment)).check(matches(isDisplayed()));
    }

    //Checks if the dropdown is clickable, and checks if it has children
    @Test
    public void dropdownTest(){
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new SettingsFragment()).commit();
        onView(withId(R.id.dropdown_menu_Settings)).check(matches(isClickable()));
        onView(withId(R.id.dropdown_menu_Settings)).check(matches(hasMinimumChildCount(1)));
    }

    //Tests if all buttons are clickable, and if they get checked after being clicked.
    @Test
    public void buttonTest(){
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new SettingsFragment()).commit();
        onView(withId(R.id.imperial_button)).check(matches(isClickable()));
        onView(withId(R.id.font_changer)).check(matches(isClickable()));


        onView(withId(R.id.imperial_button)).perform(click());
        onView(withId(R.id.imperial_button)).check(matches(isChecked()));

        onView(withId(R.id.font_changer)).perform(click());
        onView(withId(R.id.font_changer)).check(matches(isChecked()));
    }
}
