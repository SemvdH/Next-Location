package com.a1.nextlocation.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Location;

public class LocationDetailFragment extends Fragment {
    private static final String TAG = LocationDetailFragment.class.getCanonicalName();

    private ImageButton imageButton;
    private Location location;

    public LocationDetailFragment() {

    }

    public LocationDetailFragment(Location location) {
        this.location = location;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_detail, container, false);
        this.imageButton = view.findViewById(R.id.detail_location_back_button);
        this.imageButton.setOnClickListener(v -> {
            LocationFragment locationFragment = new LocationFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, locationFragment).addToBackStack(null).commit();
        });

        if (location != null) {
            Log.d(TAG, "onCreateView: the location has a name of: " + location.getName());
        }
        return view;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}