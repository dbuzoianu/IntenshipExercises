package com.example.intenshipexercises;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.widget.TextViewCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String TAG = MainActivity.class.getSimpleName();

    private int incrementValue;

    private TextView incrementTv;
    private Button incrementBtn;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("incrementValue", incrementValue);
    }

//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        incrementValue = savedInstanceState.getInt("incrementValue");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Log.d(TAG,"onCreate: Happy to be born!");
//
//        if(savedInstanceState != null)
//            incrementValue = savedInstanceState.getInt("incrementValue");
//        else
//            incrementValue = 0;
//
//        initViews();

        setContentView(R.layout.content_main);

        MapView mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        mapView.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng coord = new LatLng(41.1622023,-8.656973);
        googleMap.addMarker(new MarkerOptions().position(coord).title("Porto").snippet("Welcome to Porto, Portugal!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coord, 16));

    }

    private void initViews() {
        incrementTv = findViewById(R.id.counter_value_tv);
        incrementBtn = findViewById(R.id.increment_bt);

        incrementTv.setText(String.valueOf(incrementValue));

        incrementBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                incrementValue++;
                incrementTv.setText(incrementValue + "");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart: I exit, but you cannot see me");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume: Preparing final UI changes for you master");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause: You can see me, but I don't want to interact");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop: Packing up, preparing to leave");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: Bye Bye");
    }


}
