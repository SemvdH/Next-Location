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
import android.widget.ImageButton;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.recyclerview.RouteAdapter;
import com.a1.nextlocation.recyclerview.RouteListManager;

import java.util.List;
import com.a1.nextlocation.data.Location;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.json.DirectionsResult;
import com.a1.nextlocation.network.ApiHandler;
import com.a1.nextlocation.network.DirectionsListener;

public class RouteFragment extends Fragment {
    private static final String TAG = RouteFragment.class.getCanonicalName();

    private List<Route> routeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);

        RecyclerView routeRecyclerView = view.findViewById(R.id.route_recyclerview);
        routeRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        RouteListManager.INSTANCE.setContext(this.getContext());
        RouteListManager.INSTANCE.load();
        this.routeList = RouteListManager.INSTANCE.getRouteList();

        //Sets the adapter for the route
        RouteAdapter routeAdapter = new RouteAdapter(this.getContext(), this.routeList, clickedPosition -> {
            RouteDetailFragment routeDetailFragment = new RouteDetailFragment();
            Bundle routeBundle = new Bundle();
            routeBundle.putParcelable("route", this.routeList.get(clickedPosition));
            routeDetailFragment.setArguments(routeBundle);
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, routeDetailFragment).addToBackStack(null).commit();
        });

        ImageButton backButton = view.findViewById(R.id.route_back_button);
        backButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).addToBackStack(null).commit();
        });

        routeRecyclerView.setLayoutManager(layoutManager);
        routeRecyclerView.setAdapter(routeAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}