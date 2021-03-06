package com.a1.nextlocation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.recyclerview.LocationAdapter;
import com.a1.nextlocation.recyclerview.LocationListManager;

import java.util.List;

public class LocationFragment extends Fragment {
    private RecyclerView locationRecyclerView;
    private LocationAdapter locationAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Location> locationList;
    private ImageButton backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location, container, false);

        this.locationRecyclerView = view.findViewById(R.id.location_recyclerview);
        this.locationRecyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this.getContext());

        //Initialised the back button
        this.backButton = view.findViewById(R.id.location_back_button);
        this.backButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            if (getActivity() != null)
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).addToBackStack(null).commit();
        });

        //Loads the location list
        LocationListManager.INSTANCE.setContext(this.getContext());
        LocationListManager.INSTANCE.load();
        this.locationList = LocationListManager.INSTANCE.getLocationList();

        //Initialises the adapter
        this.locationAdapter = new LocationAdapter(this.getContext(), this.locationList, clickedPosition -> {
            LocationDetailFragment locationDetailFragment = new LocationDetailFragment(this.locationList.get(clickedPosition));
            Bundle locationBundle = new Bundle();
            //Gives the clicked location to the adapter
            locationBundle.putParcelable("location", this.locationList.get(clickedPosition));
            locationDetailFragment.setArguments(locationBundle);
            if (getActivity() != null)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, locationDetailFragment).addToBackStack(null).commit();
        });

        this.locationRecyclerView.setLayoutManager(this.layoutManager);
        this.locationRecyclerView.setAdapter(this.locationAdapter);
        return view;
    }
}