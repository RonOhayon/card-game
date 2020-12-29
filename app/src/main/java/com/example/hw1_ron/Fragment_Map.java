package com.example.hw1_ron;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Fragment_Map extends Fragment {

    protected View view;
    protected  SupportMapFragment smp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map,container,false);
        initMap();
        return view;
    }

    public void initMap(){

        smp = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.google_map);
        smp.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();

                        markerOptions.position(latLng);

                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);

                        googleMap.clear();

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                        googleMap.addMarker(markerOptions);


                    }
                });
            }
        });
    }

    public void setOnPosition(double latitude ,double longitude){
        LatLng latLng = new LatLng(latitude,longitude);
        Log.d("pff", "setOnPosition: " + latitude +":" + longitude);
        smp.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                        MarkerOptions markerOptions = new MarkerOptions();

                        markerOptions.position(latLng);

                        markerOptions.title(latLng.latitude+" : "+latLng.longitude);

                        googleMap.clear();

                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

                        googleMap.addMarker(markerOptions);


                    }
                });
            }
}

