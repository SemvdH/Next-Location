package com.a1.nextlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Coupon;
import com.a1.nextlocation.data.FileIO;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.fragments.HomeFragment;
import com.a1.nextlocation.fragments.RouteFragment;
import com.a1.nextlocation.fragments.SettingsFragment;
import com.a1.nextlocation.fragments.StatisticFragment;
import com.a1.nextlocation.network.ApiHandler;
import com.a1.nextlocation.recyclerview.CouponListManager;
import com.a1.nextlocation.recyclerview.LocationListManager;
import com.a1.nextlocation.recyclerview.RouteListManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    /**
     * onCreate method that creates the main activity
     * @param savedInstanceState the saved instance state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.navigation_bar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        /*System.out.println(Arrays.toString(getFilesDir().listFiles()));
        FileIO<Route> fileIO = new FileIO<>();
        fileIO.writeFileData(new Route("TERSTSET"), getApplicationContext());
        Log.d(TAG, "onCreate: " + "FILE GESCHREVENN!!!!!");*/

        LocationListManager.INSTANCE.setContext(this);
        LocationListManager.INSTANCE.load();
        CouponListManager.INSTANCE.setContext(this);
        CouponListManager.INSTANCE.load();
        RouteListManager.INSTANCE.setContext(this);
        RouteListManager.INSTANCE.load();

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