package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Route;

public class RouteDetailFragment extends Fragment {

    private Route route;
    private TextView routeDetailText;
    private ImageButton imageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route_detail, container, false);
        if(getArguments().getParcelable("route") != null) {
            this.route = getArguments().getParcelable("route");
        }

        this.routeDetailText = view.findViewById(R.id.reoute_detail_tekst);
        this.routeDetailText.setText(this.route.getName());

        this.imageButton = view.findViewById(R.id.route_detail_back_button);
        this.imageButton.setOnClickListener(v -> {
            RouteFragment routeFragment = new RouteFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, routeFragment).addToBackStack(null).commit();
        });


        return view;
    }
}