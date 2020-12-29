package com.example.hw1_ron;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Winner_list extends AppCompatActivity {
    Button win_BTN_close;
    FrameLayout win_LAY_list;
    FrameLayout win_LAY_map;
    Fragment_List fragment_list;
    Fragment_Map fragment_map;
    int score;
    String name;
    double lat;
    double lag;
    ArrayList<Winner> temp;

    public static final String EXTRA_KEY_SCORE = "EXTRA_KEY_SCORE";
    public static final String EXTRA_KEY_NAME = "EXTRA_KEY_NAME";
    public static final String EXTRA_KEY_LAT = "EXTRA_KEY_LAT";
    public static final String EXTRA_KEY_LONG = "EXTRA_KEY_LONG";
    public static final String EXTRA_KEY_ACT = "EXTRA_KEY_ACT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winner_list);


        findView();
        initBtn();
        initFragments();

        trackLastActivity();


    }

    private void findView(){
        win_BTN_close = findViewById(R.id.win_BTN_close);
        win_LAY_list = findViewById(R.id.win_LAY_list);
        win_LAY_map = findViewById(R.id.win_LAY_map);
    }

    private void initBtn(){
        win_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    private void closeActivity() {
        saveData();
        finish();
    }

    private void initFragments(){
        trackLastActivity();
        fragment_map = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.win_LAY_map,fragment_map).commit();

    }

    private void setWinner(){
        score = getIntent().getIntExtra(EXTRA_KEY_SCORE, -1);
        name = getIntent().getStringExtra(EXTRA_KEY_NAME);
        lat =getIntent().getDoubleExtra(EXTRA_KEY_LAT,0);
        lag = getIntent().getDoubleExtra(EXTRA_KEY_LONG,0);



    }

    private void trackLastActivity(){
        int test = getIntent().getIntExtra(EXTRA_KEY_ACT,-1);
        if (test==1) {
            setWinner();
        }
            loadData();
            fragment_list = Fragment_List.newInstance(name,score,lat,lag);
            fragment_list.setCallBack_list(callBack_list);
            getSupportFragmentManager().beginTransaction().add(R.id.win_LAY_list,fragment_list).commit();
            fragment_list.setList(temp);
            fragment_list.checkSize();

    }
    private CallBack_list callBack_list = new CallBack_list() {
        @Override
        public void setLocation(double lat, double lng) {
            fragment_map.setOnPosition(lat,lng);
        }
    };
    public void saveData() {
        SharedPreferences sp = getSharedPreferences("Sp",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(fragment_list.getList());
        editor.putString("key", json);
        editor.apply();
    }
    public void loadData() {
        SharedPreferences sp = getSharedPreferences("Sp",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("key",null);
        Type type = new TypeToken<ArrayList<Winner>>(){}.getType();
        temp = gson.fromJson(json,type);
    }
}
