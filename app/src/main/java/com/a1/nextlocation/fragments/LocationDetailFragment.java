package com.a1.nextlocation.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Location;

public class LocationDetailFragment extends Fragment {

    private ImageButton imageButton;
    private ImageView locationImage;
    private Location location;
    private TextView titelText;
    private TextView detailText;

    public LocationDetailFragment(){}

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

        this.imageButton = view.findViewById(R.id.detail_location_back_button);
        this.imageButton.setOnClickListener(v -> {
            LocationFragment locationFragment = new LocationFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, locationFragment).addToBackStack(null).commit();
        });

        this.locationImage = view.findViewById(R.id.detail_location_image);
        Context context = this.locationImage.getContext();
        int id = context.getResources().getIdentifier(this.location.getImageUrl(), "drawable", context.getPackageName());
        this.locationImage.setImageResource(id);

        return view;
    }

    public void setLocation(Location location){
        this.location = location;
    }
}