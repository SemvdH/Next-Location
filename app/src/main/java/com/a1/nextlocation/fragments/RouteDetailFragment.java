package com.a1.nextlocation.fragments;

import android.annotation.SuppressLint;
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
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.data.RouteHandler;
import com.a1.nextlocation.network.ApiHandler;

import java.util.ArrayList;
import java.util.List;

public class RouteDetailFragment extends Fragment {

    private Route route;
    private TextView routeDetailText;
    private TextView routeName;
    private TextView totalDistance;
    private ImageButton imageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("SetTextI18n")
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

        this.totalDistance = view.findViewById(R.id.total_distance);
        String distance_tekst = getResources().getString(R.string.total_distance_route);
        this.totalDistance.setText(distance_tekst + " " + calculateRoute(this.route.getLocations()) + "m");

        this.imageButton = view.findViewById(R.id.route_detail_back_button);
        this.imageButton.setOnClickListener(v -> {
            RouteFragment routeFragment = new RouteFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, routeFragment).addToBackStack(null).commit();
        });

        Button startButton = view.findViewById(R.id.start_route_button);
        startButton.setOnClickListener(this::startRoute);

        return view;
    }

    /**
     * Button onclick method that starts the route that is being viewed.
     * @param view the button
     */
    public void startRoute(View view) {
        ApiHandler.INSTANCE.getDirections(route);
        RouteHandler.INSTANCE.followRoute(route);
        Toast.makeText(requireContext(),"Route started!",Toast.LENGTH_SHORT).show();
        ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomeFragment()).addToBackStack(null).commit();
    }

    /**
     * Calculates the distance between points
     * @param route the route that is calculated
     * @return the total distance of a route
     */
    public double calculateRoute(List<Location> route){
        ArrayList<Location> routeArraylist = new ArrayList<>(route);
        double totalDistance = 0;
        Location firstLocation;
        Location secondLocation;
        System.out.println("Total locations: " + routeArraylist.size());
        //Cycles through the arraylist
        for(int i = 0; i < routeArraylist.size() - 1; i++) {
            firstLocation = routeArraylist.get(i);
            secondLocation = routeArraylist.get(i+1);
            System.out.println("locations distance calculated: " + (i+1) + " and " + (i+2) + "\nThe added distance is: " + Location.getDistance(firstLocation.getLat(), firstLocation.getLong(), secondLocation.getLat(), secondLocation.getLong()));
            //Calculates the distance between points
            totalDistance += Location.getDistance(firstLocation.getLat(), firstLocation.getLong(), secondLocation.getLat(), secondLocation.getLong());
        }
        System.out.println("Total Distance:  " + totalDistance);
        return totalDistance;
    }

}