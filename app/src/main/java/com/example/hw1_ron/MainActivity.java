package com.example.hw1_ron;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


      private Button main_BTN_start;
      private Button main_BTN_winlist;
      public static final String EXTRA_KEY_ACT = "EXTRA_KEY_ACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        findView();
        initBtn();
    }

    private void openGame() {
        Intent myIntent = new Intent(this, gameActivity.class);
        this.startActivity(myIntent);

    }
    private void openWinList() {
        Intent myIntent = new Intent(this, Winner_list.class);
        this.startActivity(myIntent);
        myIntent.putExtra(Winner_list.EXTRA_KEY_ACT,0);

    }
    private void findView(){
        main_BTN_start = findViewById(R.id.main_btn_start);
        main_BTN_winlist = findViewById(R.id.main_btn_winList);
    }
    private void initBtn(){
        main_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame();
            }
        });
        main_BTN_winlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWinList();
            }
        });
    }


}