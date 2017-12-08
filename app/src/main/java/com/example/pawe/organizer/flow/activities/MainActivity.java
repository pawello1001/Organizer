package com.example.pawe.organizer.flow.activities;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.pawe.organizer.R;
import com.example.pawe.organizer.base.MyApplication;
import com.example.pawe.organizer.base.activities.BaseActivity;
import com.example.pawe.organizer.flow.adapters.SectionsPagerAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class MainActivity extends BaseActivity implements LocationListener{

    private static final int TABS_COUNTER = 3;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private Location currentLocation;
    LocationManager locationManager;

    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.activity_main_viewpager)
    ViewPager mContainerVP;

    @BindView(R.id.activity_main_tabs)
    TabLayout mTabsTL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ActiveAndroid.initialize(this);
        setSupportActionBar(mToolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), TABS_COUNTER);
        mContainerVP.setAdapter(mSectionsPagerAdapter);

        mTabsTL.setupWithViewPager(mContainerVP);

        getCurrentLocation();
    }

    @Override
    public void onLocationChanged(Location location) {
        this.currentLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, R.string.address_no_gps, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResult) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResult);
        switch (requestCode) {
            case 1340: {
                if (grantResult.length > 0 || grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, R.string.permission_granted, Toast.LENGTH_SHORT).show();
                    getCurrentLocation();
                } else {
                    Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1340);
            return;
        }
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public void setCurrentAddress() {
        getCurrentLocation();
        if (currentLocation == null) Toast.makeText(this, R.string.address_no_gps, Toast.LENGTH_LONG).show();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
            String street = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String country = addresses.get(0).getCountryName();
            String address = street + ", " + city + ", " + country;
            Log.d("ADDRESS", address);

            com.example.pawe.organizer.models.Address add = new com.example.pawe.organizer.models.Address();
            add.setName(getString(R.string.address_name));
            add.setLatitude(currentLocation.getLatitude());
            add.setLongitude(currentLocation.getLongitude());
            add.setAddress(address);

            add.save();
        } catch (IOException ex) {
            Toast.makeText(this, R.string.address_cannot_find, Toast.LENGTH_LONG).show();
        }
    }
}
