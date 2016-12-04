package com.jzheadley.eat.data.services;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


public class LocationService extends Activity implements
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener,
    LocationListener {

    private static final String TAG = LocationService.class.getSimpleName();
    private static final int UPDATE_INTERVAL = 10000;
    private static final int FATEST_INTERVAL = 5000;
    private static final int DISPLACEMENT = 10;

    private final Activity activity;
    private Location lastLocation;
    private GoogleApiClient googleApiClient;
    private boolean requestLocationUpdates = false;
    private LocationRequest locationRequest;

    public LocationService(AppCompatActivity activity) {
        this.activity = activity;
        buildGoogleApiClient();
        googleApiClient.connect();

    }


    public Location getLocation() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;

        }
        Log.d(TAG, "getLocation: " + lastLocation);
        if (lastLocation != null) {
            return lastLocation;
        } else {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        }
        return lastLocation;
    }

    private void togglePeriodLocationUpdates() {
        if (!requestLocationUpdates) {
            requestLocationUpdates = true;
            startLocationUpdates();
        } else {
            requestLocationUpdates = false;
            stopLocationUpdates();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        Log.d(TAG, "buildGoogleApiClient: GoogleApiClient Created");
        googleApiClient = new GoogleApiClient.Builder(activity)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();
        googleApiClient.reconnect();

    }

    protected void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(20000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        } else {
            googleApiClient.connect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected: Connected to FusedLocationAPI");
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lastLocation = LocationServices.FusedLocationApi.getLastLocation(
            googleApiClient);
        if (lastLocation != null) {

            Toast.makeText(activity, "Latitude:" + lastLocation.getLatitude() + ", Longitude:"
                + lastLocation.getLongitude(), Toast.LENGTH_LONG).show();

        }

    }

    protected void stopLocationUpdates() {
        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                googleApiClient, this);
        }
    }

    @Override
    public void onConnectionSuspended(int integer) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: " + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        // Toast.makeText(activity, "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude(), Toast.LENGTH_LONG).show();
        this.lastLocation = location;
    }
}
