package com.a1.nextlocation.fragments;



import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
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
import com.a1.nextlocation.recyclerview.CustomOverlay;
import com.a1.nextlocation.recyclerview.LocationListManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private final String userAgent = "com.ai.nextlocation.fragments";
    private ImageButton imageButton;
    private MapView mapView;
    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private final String TAG = HomeFragment.class.getCanonicalName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissionsIfNecessary(
                // if you need to show the current location request FINE_LOCATION permission
                Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

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

        return view;
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
        mapView = view.findViewById(R.id.map_view);
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

        CustomOverlay customOverlay = new CustomOverlay(getResources().getDrawable(R.drawable.ic_baseline_location_on_24),mapView);

        for (com.a1.nextlocation.data.Location l : LocationListManager.INSTANCE.getLocationList()) {
            GeoPoint p = new GeoPoint(l.getLat(), l.getLong());
            OverlayItem overlayItem = new OverlayItem(l.getName(),l.getDescription(), p);

            customOverlay.addOverlayItem(overlayItem);
            Log.d(TAG, "initMap: " + "succes");
        }


        mapView.getOverlays().add(customOverlay);

        // add the zoom controller
        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);

        // add location manager and set the start point
        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);


        try {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            GeoPoint startPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
            mapController.setCenter(startPoint);

        } catch (SecurityException e) {
            Log.d(TAG, "onViewCreated: exception while getting location: " + e.getLocalizedMessage());

            requestPermissionsIfNecessary(
                    // if you need to show the current location request FINE_LOCATION permission
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    // WRITE_EXTERNAL_STORAGE is required in order to show the map
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

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
}