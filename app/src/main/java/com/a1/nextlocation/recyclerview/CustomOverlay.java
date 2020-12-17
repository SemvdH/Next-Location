package com.a1.nextlocation.recyclerview;

import android.graphics.Point;
import android.graphics.drawable.Drawable;

import org.osmdroid.api.IMapView;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class CustomOverlay extends ItemizedOverlay<OverlayItem> {
    private final MapView mapView;
    private ArrayList<OverlayItem> overlayItems = new ArrayList<>();

    public CustomOverlay(Drawable pDefaultMarker, MapView mapView) {
        super(pDefaultMarker);
        this.mapView = mapView;

    }

    public void addOverlayItem(OverlayItem item) {
        overlayItems.add(item);
        populate();
    }

    @Override
    protected OverlayItem createItem(int i) {
        return overlayItems.get(i);
    }

    @Override
    public int size() {
        return overlayItems.size();
    }

    @Override
    public boolean onSnapToItem(int x, int y, Point snapPoint, IMapView mapView) {
        return true;
    }
}
