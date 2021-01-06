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

    private RecyclerView routeRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Route> routeList;
    private RouteAdapter routeAdapter;
    private ImageButton imageButton;

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

        this.imageButton = view.findViewById(R.id.route_back_button);
        this.imageButton.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, homeFragment).addToBackStack(null).commit();
        });

        this.routeRecyclerView.setLayoutManager(this.layoutManager);
        this.routeRecyclerView.setAdapter(this.routeAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ApiHandler.INSTANCE.getDirections(51.49017262451581, 4.289038164073164,51.47337383133509, 4.303535222390562);
//        Route r = new Route("test");
//        r.addLocation(new Location("test",51.574473766034046, 4.7628379328055175,"route",null));
//        r.addLocation(new Location("test",51.577354223919876, 4.771120593941968,"route",null));
//        r.addLocation(new Location("test",51.573033468635174, 4.782750651807139,"route",null));
//        r.addLocation(new Location("test",51.56519104881196, 4.748246716295709,"route",null));
//        r.addLocation(new Location("test",51.57367360644676, 4.74404101271347,"route",null));
//        r.addLocation(new Location("test",51.57852769146427, 4.739878224473907,"route",null));
////        r.addLocation(new Location("test",51.489063681658145, 4.289596063527951,"route",null));
////        r.addLocation(new Location("test",51.483012677667766, 4.28003245468457,"route",null));
//        ApiHandler.INSTANCE.getDirections(r);
    }
}