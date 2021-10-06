package com.example.rate_the_restroom;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.rate_the_restroom.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;
    private Marker STCMarker;
    private Marker SmithMarker;

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

}