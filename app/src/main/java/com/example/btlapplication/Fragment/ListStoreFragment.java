package com.example.btlapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.btlapplication.Adapter.CityAdapter;
import com.example.btlapplication.Adapter.ListStoreAdapter;
import com.example.btlapplication.Modal.City;
import com.example.btlapplication.Modal.Restaurant;
import com.example.btlapplication.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListStoreFragment extends AppCompatActivity {
    private RecyclerView rec_liststore;
    private ListStoreAdapter listStoreAdapter;
    private List<Restaurant> mRestaurants;
    private ImageView btn_back_Liststore;
    private NavigationView nav_liststore;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_liststore);
        initUi();
        getAllListCity();
        btn_back_Liststore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initUi() {
        rec_liststore = findViewById(R.id.rec_liststore);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rec_liststore.setLayoutManager(linearLayoutManager);

        mRestaurants = new ArrayList<>();
        listStoreAdapter = new ListStoreAdapter(mRestaurants,this);

        rec_liststore.setAdapter(listStoreAdapter);
        nav_liststore = findViewById(R.id.nav_liststore);
        btn_back_Liststore = nav_liststore.getHeaderView(0).findViewById(R.id.btn_back_Liststore);

    }
    private void getAllListCity(){
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        int idCity = bundle.getInt("city_name");
        String link  = "City"+idCity;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(link);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Restaurant restaurant = snapshot.getValue(Restaurant.class);
                if (restaurant != null){
                    mRestaurants.add(restaurant);
                    listStoreAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("AAA0",error.toString());
            }
        });

    }
}
