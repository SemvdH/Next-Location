package com.a1.nextlocation.network;

import com.a1.nextlocation.json.DirectionsResult;

public interface DirectionsListener {
    void onDirectionsAvailable(DirectionsResult result);
}
