package com.a1.nextlocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.a1.nextlocation.R;
import com.a1.nextlocation.fragments.HomeFragment;
import com.a1.nextlocation.fragments.RouteFragment;
import com.a1.nextlocation.fragments.SettingsFragment;
import com.a1.nextlocation.fragments.StatisticFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    /**
     * onCreate method that creates the main activity
     * @param savedInstanceState the saved instance state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.navbar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.locations:
                selectedFragment = new HomeFragment();
                break;
            case R.id.routes:
                selectedFragment = new RouteFragment();
                break;
            case R.id.statistics:
                selectedFragment = new StatisticFragment();
                break;
            case R.id.settings:
                selectedFragment = new SettingsFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectedFragment).commit();
        return true;
    };
}