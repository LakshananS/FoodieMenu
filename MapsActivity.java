package com.example.foodiemenu;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng restaurantLocation = new LatLng(6.946600171729563, 79.86282796837145);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker for the restaurant and move the camera
        mMap.addMarker(new MarkerOptions().position(restaurantLocation).title("Foodie Restaurant"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(restaurantLocation, 18.0f));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Open Google Maps with the user's current location and directions to the restaurant
        openGoogleMaps();
    }

    private void openGoogleMaps() {
        // Create a Uri for the restaurant's location
        String restaurantUri = "geo:" + restaurantLocation.latitude + "," + restaurantLocation.longitude;

        // Create a Uri for the user's current location
        String userLocationUri = "geo:0,0?q=" + "My+Location";

        // Create a Uri for the directions to the restaurant
        String directionsUri = "https://www.google.com/maps/dir/?api=1&destination=" +
                restaurantLocation.latitude + "," + restaurantLocation.longitude;

        // Create an Intent to open Google Maps
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(restaurantUri));
        mapIntent.setPackage("com.google.android.apps.maps");

        // Check if the user has Google Maps installed
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            // Open Google Maps with the user's current location and directions to the restaurant
            mapIntent.setData(Uri.parse(directionsUri));
            startActivity(mapIntent);
        }
    }
}
