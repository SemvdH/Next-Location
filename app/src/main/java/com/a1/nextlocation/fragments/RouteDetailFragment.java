package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.data.RouteHandler;
import com.a1.nextlocation.network.ApiHandler;

public class RouteDetailFragment extends Fragment {

    private Route route;
    private TextView routeDetailText;
    private TextView routeName;
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

        this.routeName = view.findViewById(R.id.route_title);
        this.routeName.setText(this.route.getName());

        this.routeDetailText = view.findViewById(R.id.reoute_detail_tekst);
        this.routeDetailText.setText(this.route.getDescription());

        this.imageButton = view.findViewById(R.id.route_detail_back_button);
        this.imageButton.setOnClickListener(v -> {
            RouteFragment routeFragment = new RouteFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, routeFragment).addToBackStack(null).commit();
        });

        Button startButton = view.findViewById(R.id.start_route_button);
        startButton.setOnClickListener(this::startRoute);


        return view;
    }

    public void startRoute(View view) {
        ApiHandler.INSTANCE.getDirections(route);
        RouteHandler.INSTANCE.followRoute(route);
        Toast.makeText(requireContext(),"Route started!",Toast.LENGTH_SHORT).show();
        ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).addToBackStack(null).commit();

    }
}