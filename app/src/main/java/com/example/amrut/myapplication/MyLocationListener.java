package com.example.amrut.myapplication;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by amrut on 9/26/2017.
 */

public class MyLocationListener implements LocationListener{

    private String myLocation;
    public void onLocationChanged(Location location) {
        // Called when a new location is found by the network location provider.
        location.getLatitude();
        location.getLongitude();
        this.myLocation = "Latitude = "+location.getLatitude() + "Longitude = "+location.getLongitude();
        Log.e("Current location", myLocation);
    }

    public String getMyLocation() {
        return this.myLocation;
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {}

    public void onProviderEnabled(String provider) {}

    public void onProviderDisabled(String provider) {}

};
