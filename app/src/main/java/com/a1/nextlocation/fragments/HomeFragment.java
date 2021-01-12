package com.a1.nextlocation.fragments;


import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.a1.nextlocation.R;
import com.a1.nextlocation.data.Data;
import com.a1.nextlocation.data.RouteHandler;
import com.a1.nextlocation.geofencing.GeofenceInitalizer;
import com.a1.nextlocation.json.DirectionsResult;
import com.a1.nextlocation.network.ApiHandler;
import com.a1.nextlocation.recyclerview.LocationListManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements LocationListener {
    private final String userAgent = "com.ai.nextlocation.fragments";

    private ImageButton imageButton;
    private ImageButton stopButton;
    private MapView mapView;

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private final String TAG = HomeFragment.class.getCanonicalName();

    private Polyline roadOverlay;
    private int color;
    private Location currentLocation;
    private Overlay allLocationsOverlay;
    private GeofenceInitalizer initializer;
    private final static String CHANNEL_ID = "next_location01";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissionsIfNecessary(
                // if you need to show the current location request FINE_LOCATION permission
                Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        color = requireContext().getColor(R.color.red);
        Data.INSTANCE.setLocationProximityListener(this::onLocationVisited);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // set up the location list button
        this.imageButton = view.findViewById(R.id.location_list_button);
        this.imageButton.setOnClickListener(v -> {
            LocationFragment locationFragment = new LocationFragment();
            ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, locationFragment).addToBackStack(null).commit();
        });

        // set up the route stop button
        stopButton = view.findViewById(R.id.home_stop_route_button);
        stopButton.setOnClickListener(v -> {
            stopRoute();
        });

        // show or hide the stop route button based on if we are following a route
        if (RouteHandler.INSTANCE.isFollowingRoute()) {
            stopButton.setVisibility(View.VISIBLE);
        } else {
            stopButton.setVisibility(View.GONE);
        }

        //register as a listener for a result of the API
        ApiHandler.INSTANCE.addListener(this::onDirectionsAvailable);
        return view;
    }

    /**
     * stops the current route
     */
    private void stopRoute() {
        Log.d(TAG, "stopRoute: STOPPING ROUTE");
        RouteHandler.INSTANCE.finishRoute();
        stopButton.setVisibility(View.GONE);
        Toast.makeText(requireContext(), getResources().getString(R.string.route_stop_toast), Toast.LENGTH_SHORT).show();
        mapView.getOverlays().remove(roadOverlay);
        mapView.getOverlays().remove(allLocationsOverlay);
        addLocations();
        mapView.invalidate();
        roadOverlay = null;
    }

    /**
     * callback method that gets called when there are new directions available in the form of a {@link DirectionsResult} object.
     *
     * @param directionsResult the directions received from the api
     */
    private void onDirectionsAvailable(DirectionsResult directionsResult) {
        Log.d(TAG, "onDirectionsAvailable: got result! " + directionsResult);
        ArrayList<GeoPoint> geoPoints = directionsResult.getGeoPoints();
        roadOverlay = new Polyline();
        roadOverlay.setPoints(geoPoints);
        roadOverlay.setColor(color);

        // pass the line to the route handler
        RouteHandler.INSTANCE.setCurrentRouteDuration(directionsResult.getDuration());
        RouteHandler.INSTANCE.setCurrentRouteLine(roadOverlay);
        Log.d(TAG, "onDirectionsAvailable: successfully added road!");

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializer = new GeofenceInitalizer(requireContext(),requireActivity());
        initMap(view);
    }

    /**
     * This method initializes the map and all the things it needs
     *
     * @param view the view the map is on
     */
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
        CompassOverlay compassOverlay = new CompassOverlay(requireContext(), new InternalCompassOrientationProvider(requireContext()), mapView);
        compassOverlay.enableCompass();
        mapView.getOverlays().add(compassOverlay);

        addLocations();

        // add the location overlay
        MyLocationNewOverlay mLocationOverlay = new MyLocationNewOverlay(gpsMyLocationProvider, mapView);
        mLocationOverlay.enableFollowLocation();
        mLocationOverlay.enableMyLocation();
        mapView.getOverlays().add(mLocationOverlay);

        // add the zoom controller
        IMapController mapController = mapView.getController();
        if (Data.INSTANCE.getZoom() == 0) {
            Data.INSTANCE.setZoom(15.0);
        }
        mapController.setZoom(Data.INSTANCE.getZoom());

        // add location manager and set the start point
        LocationManager locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);


        try {

            // request location updates for the distance checking
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

            // get the current location and set it as center
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (currentLocation == null) currentLocation = location;

            if (location != null) {
                GeoPoint start = new GeoPoint(location.getLatitude(), location.getLongitude());
                mapController.setCenter(start);
            }

        } catch (SecurityException e) {
            Log.d(TAG, "onViewCreated: exception while getting location: " + e.getLocalizedMessage());

            requestPermissionsIfNecessary(
                    // if you need to show the current location request FINE_LOCATION permission
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    // WRITE_EXTERNAL_STORAGE is required in order to show the map
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

        }


        displayRoute();

    }



    /**
     * displays the route that is currently being followed as a red line
     */
    private void displayRoute() {

        if (RouteHandler.INSTANCE.isFollowingRoute()) {
            Log.d(TAG, "displayRoute: WE ARE FOLLOWING A ROUTE");
            if (roadOverlay == null) {
                if (RouteHandler.INSTANCE.getCurrentRouteLine() != null) {
                    roadOverlay = RouteHandler.INSTANCE.getCurrentRouteLine();
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
    }

    /**
     * adds the locations of the current route to the map. If there is no current route, show all locations
     */
    private void addLocations() {
        // get the locations of the current route or all locations
        List<com.a1.nextlocation.data.Location> locations = RouteHandler.INSTANCE.isFollowingRoute() ? RouteHandler.INSTANCE.getCurrentRoute().getLocations() : LocationListManager.INSTANCE.getLocationList();
        initializer.removeGeoFences();
        final ArrayList<OverlayItem> items = new ArrayList<>(locations.size());
        // marker icon
        Drawable marker = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_location_on_24);
        marker.setAlpha(255);
        marker.setTint(getResources().getColor(R.color.secondaryColour));

        // add all locations to the overlay itemss
        for (com.a1.nextlocation.data.Location location : locations) {
            OverlayItem item = new OverlayItem(location.getName(), location.getDescription(), location.convertToGeoPoint());
            item.setMarker(marker);
            items.add(item);
        }

        // create the overlay that will hold all locations and add listeners
        allLocationsOverlay = new ItemizedIconOverlay<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    /**
                     * on sinlge click, navigate to that location's detail fragment
                     * @param index the index in the location list
                     * @param item the item that was clicked
                     * @return true
                     */
                    @Override
                    public boolean onItemSingleTapUp(int index, OverlayItem item) {
                        com.a1.nextlocation.data.Location clicked = locations.get(index);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new LocationDetailFragment(clicked)).commit();
                        return true;
                    }

                    /**
                     * on item long press, show that location's name in a toast message
                     * @param index the index in the location list
                     * @param item the item that was clicked
                     * @return true
                     */
                    @Override
                    public boolean onItemLongPress(int index, OverlayItem item) {
                        com.a1.nextlocation.data.Location clicked = locations.get(index);
                        Toast.makeText(requireContext(), clicked.getName(), Toast.LENGTH_SHORT).show();

                        // create a route to the clicked location, didn't work and didn't have enough time to make it work ¯\_(ツ)_/¯

//                        Route route = new Route("Route to " + clicked.getName());
//                        route.addLocation(new com.a1.nextlocation.data.Location("Current location",currentLocation.getLatitude(),currentLocation.getLongitude(),"your location",null));
//                        route.addLocation(clicked);
//                        ApiHandler.INSTANCE.getDirections(route);
                        return true;
                    }
                }, requireContext());

        // add the overlay to the map
        mapView.getOverlays().add(allLocationsOverlay);
        Log.d(TAG, "addLocations: successfully added locations");

        addGeofences(locations);

    }

    /**
     * adds the geofences for the currently active locations
     * @param locations the locations to add geofences for
     */
    private void addGeofences(List<com.a1.nextlocation.data.Location> locations) {

        Log.d(TAG, "addGeofences: adding geofences!");

        initializer.init(locations);
    }

    /**
     * @param permissions tbe permissions we want to ask
     * @author Ricky
     * request the permissions needed for location and network, made by Ricky
     */
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

    /**
     * location callback that gets called each time the location is updated. It is used for updating the distance walked and checking if there are locations you have visited
     *
     * @param location the new location
     */
    @Override
    public void onLocationChanged(@NonNull Location location) {
        // calculate the distance walked

        if (currentLocation != null) {
            double distance = currentLocation.distanceTo(location); // in meters
            // can't walk 100 meters in a few seconds
            if (distance < 100) {
                Data.INSTANCE.addDistance(distance);
            }
        }
        currentLocation = location;


        //new thread because we don't want the main thread to hang, this method gets called a lot
        Thread t = new Thread(() -> {
            com.a1.nextlocation.data.Location last = null;
            if (RouteHandler.INSTANCE.isFollowingRoute()) {
                List<com.a1.nextlocation.data.Location> locs = RouteHandler.INSTANCE.getCurrentRoute().getLocations();
                last = locs.get(locs.size() - 1);
            }

            for (com.a1.nextlocation.data.Location l : LocationListManager.INSTANCE.getLocationList()) {
                // mark the location visited if we are less than 20 meters away
                if (com.a1.nextlocation.data.Location.getDistance(currentLocation.getLatitude(), currentLocation.getLongitude(), l.getLat(), l.getLong()) < 20) {
                    Data.INSTANCE.visitLocation(l);
                    if (l.equals(last)) stopRoute();
                }
            }

            Data.INSTANCE.setZoom(mapView.getZoomLevelDouble());
        });

        t.start();

    }

    public void onLocationVisited(com.a1.nextlocation.data.Location location) {
        Data.INSTANCE.visitLocation(location);
        showNotification(location);

    }

    private void showNotification(com.a1.nextlocation.data.Location location) {

        NotificationManager mNotificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "next_location", importance);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(requireContext(),CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text,location.getName()))
                .setAutoCancel(true);

        mNotificationManager.notify(0,mBuilder.build());
    }

    // empty override methods for the LocationListener

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    /**
     * method that gets called when the app gets paused
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * method that gets called when the app gets resumed
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
}