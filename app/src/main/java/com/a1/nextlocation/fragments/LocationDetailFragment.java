package com.a1.nextlocation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.recyclerview.LocationListManager;

public class LocationDetailFragment extends Fragment {
    private static final String TAG = LocationDetailFragment.class.getCanonicalName();

    private ImageButton backButton;
    private Location location;
    private TextView titelText;
    private TextView detailText;
    private ImageView locationImage;

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

        this.titelText = view.findViewById(R.id.detail_location_name);
        this.titelText.setText(location.getName());

        this.detailText = view.findViewById(R.id.detail_location_text);
        this.detailText.setText(location.getDescription());

        //Initialises the back button
        this.backButton = view.findViewById(R.id.detail_location_back_button);
        this.backButton.setOnClickListener(v -> {
            LocationFragment locationFragment = new LocationFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, locationFragment).addToBackStack(null).commit();
        });

        //Logs the location
        if (location != null) {
            Log.d(TAG, "onCreateView: the location has a name of: " + location.getName());
        }

        this.locationImage = view.findViewById(R.id.detail_location_image);
        int id;
        if (this.location.getImageUrl() == null) {
            String newUrl = LocationListManager.INSTANCE.findByName(this.location.getName()).getImageUrl();
            id = requireContext().getResources().getIdentifier(newUrl, "drawable", requireContext().getPackageName());
        } else {
            id = requireContext().getResources().getIdentifier(this.location.getImageUrl(), "drawable", requireContext().getPackageName());
        }
        this.locationImage.setImageResource(id);

        return view;
    }
}