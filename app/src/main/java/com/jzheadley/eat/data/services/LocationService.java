package com.jzheadley.eat.data.services;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import android.support.v7.app.AppCompatActivity;

public class LocationService {
    private GoogleApiClient googleApiClient;

    public LocationService(AppCompatActivity activity) {
        googleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API)
                .build();
        // LocationManager service = (LocationManager)
        //     activity.getSystemService(Context.LOCATION_SERVICE);
        // boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // if (!enabled) {
        //     Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        //     activity.startActivity(intent);
    }

}
