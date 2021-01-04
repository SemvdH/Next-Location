package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.recyclerview.RouteAdapter;
import com.a1.nextlocation.recyclerview.RouteListManager;

import java.util.ArrayList;
import java.util.List;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.json.DirectionsResult;
import com.a1.nextlocation.network.ApiHandler;
import com.a1.nextlocation.network.DirectionsListener;

public class RouteFragment extends Fragment {
    private static final String TAG = RouteFragment.class.getCanonicalName();

    private RecyclerView routeRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Route> routeList;
    private RouteAdapter routeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiHandler.INSTANCE.addListener(this::onDirectionsAvailable);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);

        this.routeRecyclerView = view.findViewById(R.id.routeRecyclerView);
        this.routeRecyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this.getContext());

        RouteListManager.INSTANCE.setContext(this.getContext());
        RouteListManager.INSTANCE.load();
        this.routeList = RouteListManager.INSTANCE.getRouteList();

        this.routeAdapter = new RouteAdapter(this.getContext(), this.routeList, clickedPosition -> {
            RouteDetailFragment routeDetailFragment = new RouteDetailFragment();
            Bundle routeBundle = new Bundle();
            routeBundle.putParcelable("route", this.routeList.get(clickedPosition));
            routeDetailFragment.setArguments(routeBundle);
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, routeDetailFragment).addToBackStack(null).commit();
        });

        this.routeRecyclerView.setLayoutManager(this.layoutManager);
        this.routeRecyclerView.setAdapter(this.routeAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ApiHandler.INSTANCE.getDirections(8.681436,49.41461,8.687872,49.420318);
//        Route r = new Route("test");
//        r.addLocation(new Location("test",8.681436,49.41461,"route",null));
//        r.addLocation(new Location("test",8.687872,49.420318,"route",null));
//        ApiHandler.INSTANCE.getDirections(r);
    }

    public void onDirectionsAvailable(DirectionsResult result) {
        Log.d(TAG, "onDirectionsAvailable: got result! " + result);


    }
}