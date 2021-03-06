package com.a1.nextlocation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.a1.nextlocation.data.Data;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.fragments.HelpPopup;
import com.a1.nextlocation.fragments.HomeFragment;
import com.a1.nextlocation.fragments.LocationFragment;
import com.a1.nextlocation.fragments.Refreshable;
import com.a1.nextlocation.fragments.RouteFragment;
import com.a1.nextlocation.fragments.SettingsFragment;
import com.a1.nextlocation.fragments.StatisticFragment;
import com.a1.nextlocation.recyclerview.CouponListManager;
import com.a1.nextlocation.recyclerview.LocationListManager;
import com.a1.nextlocation.recyclerview.RouteListManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Refreshable {
    private static final String TAG = MainActivity.class.getName();
    private BottomNavigationView bottomNav;
    private ImageButton infoButton;
    private HomeFragment homeFragment = new HomeFragment();
    private RouteFragment routeFragment = new RouteFragment();
    private StatisticFragment statisticFragment = new StatisticFragment();
    private SettingsFragment settingsFragment = new SettingsFragment();


    /**
     * onCreate method that creates the main activity
     *
     * @param savedInstanceState the saved instance state of the app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize saved language from sharedPreferences
        setLocale(loadLocale());

        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.navigation_bar);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        infoButton = findViewById(R.id.info_button);
        infoButton.setOnClickListener(onInfoClickListener);

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
        Data.INSTANCE.setContext(this);
        Data.INSTANCE.load();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).commit();
        }
    }

    /**
     * loads the saved language from SharedPreferences
     *
     * @return the language as string
     */
    private String loadLocale() {
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        return sharedPreferences.getString("Language", "nl");
    }

    /**
     * sets the language of the application to the desired one
     *
     * @param language the desired language
     */
    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
    }


    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.locations:
                selectedFragment = homeFragment;
                break;
            case R.id.routes:
                selectedFragment = routeFragment;
                break;
            case R.id.statistics:
                selectedFragment = statisticFragment;
                break;
            case R.id.settings:
                selectedFragment = settingsFragment;
                break;
        }

        if (!selectedFragment.isVisible() && selectedFragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectedFragment).commit();
            return true;
        }
        return false;
    };

    /**
     * refreshes bottom navigation
     */
    @Override
    public void refreshAndNavigateTo(int id) {
        this.setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = (BottomNavigationView)  this.findViewById(R.id.navigation_bar);
        navigationView.setSelectedItemId(id);
        navigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private final View.OnClickListener onInfoClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

//            AlertDialog.Builder infoBuilder = new AlertDialog.Builder(getBaseContext());
//            infoBuilder.setCancelable(true);
////            infoBuilder.setPositiveButton(R.string.activate, (dialog, which) -> {
////                dialog.cancel();
////            });
//            infoBuilder.setView(getLayoutInflater().inflate(R.layout.help_popup, null));
//            AlertDialog infoPopup = infoBuilder.create();
//            infoPopup.show();

            FragmentManager fragment = getSupportFragmentManager();
            DialogFragment helpPopupFragment = new HelpPopup();
            helpPopupFragment.show(fragment, "");
        }
    };
}