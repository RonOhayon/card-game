package com.example.hw1_ron;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class End_screen extends AppCompatActivity {
    public static final String EXTRA_KEY_SCORE = "EXTRA_KEY_SCORE";
    public static final String EXTRA_KEY_WIN = "EXTRA_KEY_WIN";
    private TextView  end_LBL_result;
    private Button end_BTN_close;
    private Button end_BTN_winlist;
    private ImageView end_IMG_pirate;
    private ImageView end_IMG_indian;
    int score;
    int winCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        winCon = getIntent().getIntExtra(EXTRA_KEY_WIN, -1);
        score = getIntent().getIntExtra(EXTRA_KEY_SCORE, -1);
        setContentView(R.layout.activity_end_screen);
        initView();
        initButton();
        setWinner(score);

    }

    public void setWinner(int winCon){
        switch (score){
            case 0:
                end_LBL_result.setText("The Winner Is DirtyJack");
                end_IMG_pirate.setImageResource(R.drawable.ani_pirate_att);
                end_IMG_indian.setImageResource(R.drawable.ani_indian_dying);
                AnimationDrawable attPirate =(AnimationDrawable)end_IMG_pirate.getDrawable();
                attPirate.start();
                AnimationDrawable dyingIndi =(AnimationDrawable)end_IMG_indian.getDrawable();
                dyingIndi.start();
                break;
            case 1:
                end_LBL_result.setText("The Winner Is WildHorse");
                end_IMG_indian.setImageResource(R.drawable.ani_indian_att);
                end_IMG_pirate.setImageResource(R.drawable.ani_pirate_dying);
                AnimationDrawable attIndian =(AnimationDrawable)end_IMG_indian.getDrawable();
                attIndian.start();
                AnimationDrawable dyingPirate =(AnimationDrawable)end_IMG_pirate.getDrawable();
               dyingPirate.start();

                break;
            case 2:
                end_LBL_result.setText("Its a Tie !!!");

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + score);
        }
    }

    private void closeActivity() {
        finish();
    }

    private void initView(){
        end_LBL_result = findViewById(R.id.end_LBL_result);
        end_BTN_close = findViewById(R.id.end_BTN_close);
        end_BTN_winlist =findViewById(R.id.end_BTN_win_list);
        end_IMG_indian = findViewById(R.id.end_IMG_indian);
        end_IMG_pirate = findViewById(R.id.end_IMG_pirate);
        end_LBL_result = findViewById(R.id.end_LBL_result);
    }

    private void initButton(){

        end_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        end_BTN_winlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    private void openWinList() {
        String name = winCon==1 ? "DirtyJack" : "WildHorse";
        Intent myIntent = new Intent(this, Winner_list.class);
        myIntent.putExtra(Winner_list.EXTRA_KEY_SCORE,score);
        myIntent.putExtra(Winner_list.EXTRA_KEY_NAME,name);
        this.startActivity(myIntent);

    }
}