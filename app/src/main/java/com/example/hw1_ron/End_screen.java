package com.example.hw1_ron;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class End_screen extends AppCompatActivity {
    public static final String EXTRA_KEY_SCORE = "EXTRA_KEY_SCORE";
    public static final String EXTRA_KEY_WIN = "EXTRA_KEY_WIN";
    public static final String EXTRA_KEY_LAT = "EXTRA_KEY_LAT";
    public static final String EXTRA_KEY_LONG = "EXTRA_KEY_LONG";
    public static final String EXTRA_KEY_ACT = "EXTRA_KEY_ACT";
    private TextView end_LBL_result;
    private Button end_BTN_close;
    private Button end_BTN_winlist;
    private ImageView end_IMG_pirate;
    private ImageView end_IMG_indian;
    private double latitude;
    private double longitude;
    int score;
    int winCon;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_screen);
        winCon = getIntent().getIntExtra(EXTRA_KEY_WIN, -1);
        score = getIntent().getIntExtra(EXTRA_KEY_SCORE, -1);
        initView();
        initButton();
        setWinner(winCon);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



    }

    public void setWinner(int winCon) {
        switch (winCon) {
            case 0:
                pirateWin();
                break;
            case 1:
                indianWin();
                break;
            case 2:
                end_LBL_result.setText("Its a Tie !!!");

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + score);
        }
    }

    private void pirateWin() {
        end_LBL_result.setText("The Winner Is DirtyJack");
        end_IMG_pirate.setImageResource(R.drawable.ani_pirate_att);
        end_IMG_indian.setImageResource(R.drawable.ani_indian_dying);
        AnimationDrawable attPirate = (AnimationDrawable) end_IMG_pirate.getDrawable();
        attPirate.start();
        AnimationDrawable dyingIndi = (AnimationDrawable) end_IMG_indian.getDrawable();
        dyingIndi.start();
    }

    private void indianWin() {
        end_LBL_result.setText("The Winner Is WildHorse");
        end_IMG_indian.setImageResource(R.drawable.ani_indian_att);
        end_IMG_pirate.setImageResource(R.drawable.ani_pirate_dying);
        AnimationDrawable attIndian = (AnimationDrawable) end_IMG_indian.getDrawable();
        attIndian.start();
        AnimationDrawable dyingPirate = (AnimationDrawable) end_IMG_pirate.getDrawable();
        dyingPirate.start();
    }

    private void closeActivity() {
        finish();
    }

    private void initView() {
        end_LBL_result = findViewById(R.id.end_LBL_result);
        end_BTN_close = findViewById(R.id.end_BTN_close);
        end_BTN_winlist = findViewById(R.id.end_BTN_win_list);
        end_IMG_indian = findViewById(R.id.end_IMG_indian);
        end_IMG_pirate = findViewById(R.id.end_IMG_pirate);
        end_LBL_result = findViewById(R.id.end_LBL_result);
    }

    private void initButton() {

        end_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        end_BTN_winlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (winCon < 2) {
                    openWinListWithWinner();
                } else openWinListWithOut();
                closeActivity();
            }
        });

    }

    private void openWinListWithWinner() {
        getLocation();
        String name = winCon == 1 ? "DirtyJack" : "WildHorse";
        Intent myIntent = new Intent(this, Winner_list.class);
        myIntent.putExtra(Winner_list.EXTRA_KEY_SCORE, score);
        myIntent.putExtra(Winner_list.EXTRA_KEY_NAME, name);
        myIntent.putExtra(Winner_list.EXTRA_KEY_LAT, latitude);
        myIntent.putExtra(Winner_list.EXTRA_KEY_LONG, longitude);
        myIntent.putExtra(Winner_list.EXTRA_KEY_ACT, 1);

        this.startActivity(myIntent);
    }

    private void openWinListWithOut() {
        Intent myIntent = new Intent(this, Winner_list.class);
        this.startActivity(myIntent);
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(End_screen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(End_screen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }


        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(End_screen.this, Locale.getDefault());
                    Locale.getDefault();
                    try {
                        List<Address> address = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        latitude = address.get(0).getLatitude();
                        longitude = address.get(0).getLongitude();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}