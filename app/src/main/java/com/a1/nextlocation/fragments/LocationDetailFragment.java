package com.a1.nextlocation.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Location;

public class LocationDetailFragment extends Fragment {

    private ImageButton imageButton;
    private ImageView locationImage;
    private Location location;

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
        System.out.println( getActivity().getPackageName() + "\n" + "drawable/" + location.getImageUrl());

        this.locationImage = view.findViewById(R.id.detail_location_image);
        this.locationImage.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("drawable/" + location.getImageUrl(), "drawable", getActivity().getPackageName())));


//        flag.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("drawable/" + country_variable, "drawable", getPackageName()));

        return view;
    }

    public void setLocation(Location location) {
        this.location = location;
    };

}