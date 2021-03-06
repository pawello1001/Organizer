package com.example.pawe.organizer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.pawe.organizer.utils.OnlineChecker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String name, address;
    private double latitude, longitude;
    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);

        name = getIntent().getExtras().getString("name");
        address = getIntent().getExtras().getString("address");
        latitude = getIntent().getExtras().getDouble("lat");
        longitude = getIntent().getExtras().getDouble("long");
        latLng = new LatLng(latitude, longitude);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(53.153818,  23.087322);
        mMap.addMarker(new MarkerOptions().position(latLng).title(name + ": " + address));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
}
