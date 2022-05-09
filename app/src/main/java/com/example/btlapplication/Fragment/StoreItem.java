package com.example.btlapplication.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.btlapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreItem extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;
    SupportMapFragment supportMapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_item);
        initUI();

    }
    public void initUI(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapMain);
        supportMapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(21.028099080234878, 105.83536828816214);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
        mMap.addMarker(new MarkerOptions().position(latLng).title("Văn miếu").snippet("Văn Miếu Quốc từ giám"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}