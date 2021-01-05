package com.a1.nextlocation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.recyclerview.RouteAdapter;
import com.a1.nextlocation.recyclerview.RouteListManager;

import java.util.List;

public class RouteFragment extends Fragment {

    private RecyclerView routeRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Route> routeList;
    private RouteAdapter routeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_route, container, false);

        this.routeRecyclerView = view.findViewById(R.id.route_recyclerview);
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
}