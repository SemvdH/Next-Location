package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1.nextlocation.R;
import com.a1.nextlocation.json.DirectionsResult;
import com.a1.nextlocation.network.ApiHandler;
import com.a1.nextlocation.network.DirectionsListener;

public class RouteFragment extends Fragment {
    private static final String TAG = RouteFragment.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiHandler.INSTANCE.addListener(this::onDirectionsAvailable);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_route, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiHandler.INSTANCE.getDirections(8.681436,49.41461,8.687872,49.420318);
    }

    public void onDirectionsAvailable(DirectionsResult result) {
        Log.d(TAG, "onDirectionsAvailable: got result! " + result);

    }
}