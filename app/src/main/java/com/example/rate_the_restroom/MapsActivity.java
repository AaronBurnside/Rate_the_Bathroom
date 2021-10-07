package com.example.rate_the_restroom;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.rate_the_restroom.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private static final String TAG = MapsActivity.class.getSimpleName();

    private GoogleMap mMap;
    private Marker STCMarker;
    private Marker SmithMarker;
    private Marker CurrentLocation;
    private boolean locationPermissionGranted;
    private Location lastKnownLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private ActivityMapsBinding binding;
    static final LatLng CP1 = new LatLng(43.811452, -111.786787);
    static final LatLng CP2 = new LatLng(43.821926, -111.786798);
    static final LatLng CP3 = new LatLng(43.821791, -111.778140);
    static final LatLng CP4 = new LatLng(43.811542, -111.778128);
    static final LatLng CentP = new LatLng(43.817700, -111.783912);
    static final LatLng SmithBuilding = new LatLng(43.819198, -111.781492);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        // Add a marker in Sydney and move the camera
        LatLng STCBuilding = new LatLng(43.814729, -111.784617);
        STCMarker = mMap.addMarker(new MarkerOptions().position(STCBuilding).title("Science and Technology Center"));
        SmithMarker = mMap.addMarker(new MarkerOptions().position(SmithBuilding).title("Joseph Fielding Smith Building"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CentP, 19));
        getLocationPermission();
        getDeviceLocation();


    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(STCMarker))
        {
            Intent intent = new Intent(this, Select_Floor.class);
            startActivity(intent);
            // activate next activity
        }
        if (marker.equals(STCMarker))
        {
            Intent intent = new Intent(this, Select_Floor.class); //TODO change to different floor activity
            startActivity(intent);
            // activate next activity
        }
        return false;
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                LatLng CurrentCord = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                                CurrentLocation = mMap.addMarker(new MarkerOptions().position(CurrentCord).title("your Location"));
                                //TODO Change Marker image into current location
                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


}