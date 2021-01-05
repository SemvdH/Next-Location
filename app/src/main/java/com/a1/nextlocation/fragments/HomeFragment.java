package com.a1.nextlocation.fragments;



import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.CurrentRoute;
import com.a1.nextlocation.data.Route;
import com.a1.nextlocation.json.DirectionsResult;
import com.a1.nextlocation.network.ApiHandler;
import com.a1.nextlocation.recyclerview.CouponListManager;
import com.a1.nextlocation.recyclerview.CustomOverlay;
import com.a1.nextlocation.recyclerview.LocationListManager;
import com.a1.nextlocation.recyclerview.RouteListManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.MapQuestRoadManager;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment implements LocationListener {
    private final String userAgent = "com.ai.nextlocation.fragments";
    public final static String MAPQUEST_API_KEY = "vuyXjqnAADpjeL9QwtgWGleIk95e36My";
    private ImageButton imageButton;
    private MapView mapView;
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private final String TAG = HomeFragment.class.getCanonicalName();
    private RoadManager roadManager;
    private Polyline roadOverlay;
    private int color;

    private GeoPoint currentLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissionsIfNecessary(
                // if you need to show the current location request FINE_LOCATION permission
                Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        roadManager = new MapQuestRoadManager(MAPQUEST_API_KEY);
        roadManager.addRequestOption("routeType=foot-walking");

        color = requireContext().getColor(R.color.red);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.imageButton = view.findViewById(R.id.location_list_button);
        this.imageButton.setOnClickListener(v -> {
            LocationFragment locationFragment = new LocationFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, locationFragment).addToBackStack(null).commit();
        });


//        roadManager.addRequestOption("routeType=foot-walking");
        ApiHandler.INSTANCE.addListener(this::onDirectionsAvailable);
        return view;
    }

    private void onDirectionsAvailable(DirectionsResult directionsResult) {
        Log.d(TAG, "onDirectionsAvailable: got result! " + directionsResult);
        ArrayList<GeoPoint> geoPoints = directionsResult.getGeoPoints();
        roadOverlay = new Polyline();
        roadOverlay.setPoints(geoPoints);

        // this is for mapquest, but it gives a "no value for guidancelinkcollection" error and google has never heard of that
//        GeoPoint[] gp = directionsResult.getStartAndEndPoint();
//        ArrayList<GeoPoint> arrayList = new ArrayList<>(Arrays.asList(gp));
//        Road road = roadManager.getRoad(arrayList);
//        roadOverlay = RoadManager.buildRoadOverlay(road);

        roadOverlay.setColor(color);


        CurrentRoute.INSTANCE.setCurrentRoute(roadOverlay);
        Log.d(TAG, "onDirectionsAvailable: successfully added road!");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initMap(view);
    }

    private void initMap(@NonNull View view) {
        // set the user agent
        Configuration.getInstance().setUserAgentValue(userAgent);

        // create the map view
        mapView = view.findViewById(R.id.mapView);
        mapView.setDestroyMode(false);
        mapView.setTag("mapView");
        mapView.setMultiTouchControls(true);

        // get the location provider
        GpsMyLocationProvider gpsMyLocationProvider = new GpsMyLocationProvider(this.requireContext());

        // add the compass overlay
        CompassOverlay compassOverlay = new CompassOverlay(requireContext(),new InternalCompassOrientationProvider(requireContext()),mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);

        // add the location overlay
        MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay(gpsMyLocationProvider, mapView);
        mLocationOverlay.enableFollowLocation();
        mLocationOverlay.enableMyLocation();
        mapView.getOverlays().add(mLocationOverlay);

        // add the zoom controller
        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);

        // add location manager and set the start point
        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);



        try {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if( location != null ) {
                currentLocation = new GeoPoint(location.getLatitude(), location.getLongitude());
                mapController.setCenter(currentLocation);
            }

        } catch (SecurityException e) {
            Log.d(TAG, "onViewCreated: exception while getting location: " + e.getLocalizedMessage());

            requestPermissionsIfNecessary(
                    // if you need to show the current location request FINE_LOCATION permission
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    // WRITE_EXTERNAL_STORAGE is required in order to show the map
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }

        if (roadOverlay == null) {
            if (CurrentRoute.INSTANCE.getCurrentRoute() != null) {
                roadOverlay = CurrentRoute.INSTANCE.getCurrentRoute();
                mapView.getOverlays().add(roadOverlay);
                mapView.invalidate();
                Log.d(TAG, "initMap: successfully added road!");
            }
        } else {
            mapView.getOverlays().add(roadOverlay);
            mapView.invalidate();
            Log.d(TAG, "initMap: successfully added road!");
        }

    }
    private void requestPermissionsIfNecessary(String... permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        if (this.getContext() != null)
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(this.getContext(), permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    permissionsToRequest.add(permission);
                }
            }
        if (permissionsToRequest.size() > 0 && this.getActivity() != null) {
            ActivityCompat.requestPermissions(
                    this.getActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        currentLocation = new GeoPoint(location);
        IMapController mapController = mapView.getController();
        mapController.animateTo(new GeoPoint(location.getLatitude(),location.getLongitude()));
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    // this is deprecated but android is stupid and gives an error if I don't override it
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }
}