package com.a1.nextlocation.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.data.RouteHandler;
import com.a1.nextlocation.network.ApiHandler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RouteDetailFragment extends Fragment {

    private Route route;
    private Refreshable refreshable;
    private String time;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        if (context instanceof Refreshable)
            this.refreshable = (Refreshable) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route_detail, container, false);
        if (getArguments().getParcelable("route") != null) {
            this.route = getArguments().getParcelable("route");
        }

        //Sets the image of the RouteDetail
        ImageView imageView = view.findViewById(R.id.route_detail_image);
        int id = requireContext().getResources().getIdentifier(this.route.getImageURL(), "drawable", requireContext().getPackageName());
        imageView.setImageResource(id);

        TextView routeName = view.findViewById(R.id.route_title);
        routeName.setText(this.route.getName());

        TextView routeDetailText = view.findViewById(R.id.route_detail_tekst);
        StringBuilder locations = new StringBuilder();
        for (Location location : this.route.getLocations()) {
            locations.append("<br>•").append(location.getName());
        }
        String detailText = this.route.getDescription() + "<br><br><b>" + getResources().getString(R.string.following_locations) + "</b>" + locations + "<br><br><b>" + getResources().getString(R.string.start_location) + ": </b>" + route.getLocations().get(0).getName() + "<br>" + "<b>" + getResources().getString(R.string.end_location) + ": </b>" + route.getLocations().get(route.getLocations().size() - 1).getName();
        routeDetailText.setText(Html.fromHtml(detailText));

        //sets the text of the totaldistance
        TextView totalDistance = view.findViewById(R.id.total_distance);
        //looks if imperial units or metric
        boolean imperialChecked = getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).getBoolean("imperialSwitch", false);
        totalDistance.setText(getResources().getString(R.string.total_distance_route) + " " + String.format("%.1f", calculateRoute(this.route.getLocations())) + (imperialChecked ? "yd" : "m") + "\n" + getResources().getString(R.string.total_time) + " " + this.time);

        //Initialises the back button
        ImageButton backButton = view.findViewById(R.id.route_detail_back_button);
        backButton.setOnClickListener(v -> {
            RouteFragment routeFragment = new RouteFragment();
            if (getActivity() != null)
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, routeFragment).addToBackStack(null).commit();
        });

        Button startButton = view.findViewById(R.id.start_route_button);
        startButton.setOnClickListener(this::startRoute);

        return view;
    }

    /**
     * Button onclick method that starts the route that is being viewed.
     *
     * @param view the button
     */
    public void startRoute(View view) {
        ApiHandler.INSTANCE.getDirections(route);
        RouteHandler.INSTANCE.followRoute(route);
        Toast.makeText(requireContext(), "Route started!", Toast.LENGTH_SHORT).show();
        // navigates to the HomeFragment and refreshes the BottomNavigation
        refreshable.refreshAndNavigateTo(R.id.locations);
    }

    /**
     * Calculates the distance between points
     *
     * @param route the route that is calculated
     * @return the total distance of a route
     */
    public double calculateRoute(List<Location> route) {
        ArrayList<Location> routeArraylist = new ArrayList<>(route);
        double totalDistance = 0;
        Location firstLocation;
        Location secondLocation;
        System.out.println("Total locations: " + routeArraylist.size());
        //Cycles through the arraylist
        for (int i = 0; i < routeArraylist.size() - 1; i++) {
            firstLocation = routeArraylist.get(i);
            secondLocation = routeArraylist.get(i + 1);
            System.out.println("locations distance calculated: " + (i + 1) + " and " + (i + 2) + "\nThe added distance is: " + Location.getDistance(firstLocation.getLat(), firstLocation.getLong(), secondLocation.getLat(), secondLocation.getLong()));
            //Calculates the distance between points
            totalDistance += Location.getDistance(firstLocation.getLat(), firstLocation.getLong(), secondLocation.getLat(), secondLocation.getLong());
        }
        System.out.println("Total Distance:  " + totalDistance);

        calculateTime(totalDistance);
        // if the imperialSwitch is checked, return feet, if not, return meters
        if (getContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).getBoolean("imperialSwitch", false))
            return totalDistance * 1.0936133;
        else
            return totalDistance;
    }

    @SuppressLint("DefaultLocale")
    public void calculateTime(double totalDistance){
        double totalTimeInMinutes = ((totalDistance / 1000) / 5) * 60;
        if(totalTimeInMinutes > 60) {
            int hours = (int)(totalTimeInMinutes / 60);
            int minutes = (int)(totalTimeInMinutes % 60);
            this.time = hours + " " + getResources().getString(R.string.hour) + " " + minutes + " " +  getResources().getString(R.string.minutes);
        }
        else this.time = (int)(((totalDistance / 1000) / 5) * 60) + " " + getResources().getString(R.string.minutes);
    }

}