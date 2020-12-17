package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.recyclerview.CouponAdapter;
import com.a1.nextlocation.recyclerview.LocationAdapter;
import com.a1.nextlocation.recyclerview.LocationListManager;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends Fragment {
    private RecyclerView locationRecyclerView;
    private LocationAdapter locationAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Location> locationList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);

        locationRecyclerView = view.findViewById(R.id.locationRecyclerView);
        locationRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getContext());
        LocationListManager locationListManager = new LocationListManager(this.getContext());
        locationListManager.load();
        locationList = locationListManager.getLocationList();
        locationAdapter = new LocationAdapter(this.getContext(), locationList, clickedPosition -> {
            LocationDetailFragment locationDetailFragment = new LocationDetailFragment();
            Bundle locationBundle = new Bundle();
            locationBundle.putParcelable("location", locationList.get(clickedPosition));
            locationDetailFragment.setArguments(locationBundle);
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, locationDetailFragment).addToBackStack(null).commit();
        });
        locationRecyclerView.setLayoutManager(layoutManager);
        locationRecyclerView.setAdapter(locationAdapter);
        return view;
    }
}