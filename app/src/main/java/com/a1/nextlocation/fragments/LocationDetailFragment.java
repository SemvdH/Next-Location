package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.a1.nextlocation.R;

public class LocationDetailFragment extends Fragment {

    private ImageButton imageButton;

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

        return view;
    }
}