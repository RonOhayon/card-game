package com.example.hw1_ron;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class End_screen extends AppCompatActivity {
    public static final String EXTRA_KEY_SCORE = "EXTRA_KEY_SCORE" ;
    private ImageView end_IMG_medal;
    private ImageView end_IMG_player;
    private TextView  end_LBL_result;
    private Button end_BTN_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end_screen);

        end_IMG_medal = findViewById(R.id.end_IMG_medal);
        end_IMG_player = findViewById(R.id.end_IMG_player);
        end_LBL_result = findViewById(R.id.end_LBL_result);
        end_BTN_close = findViewById(R.id.end_BTN_close);

        int score = getIntent().getIntExtra(EXTRA_KEY_SCORE, -1);
        setWinner(score);


        end_BTN_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
    }

    public void setWinner(int score){
        switch (score){
            case 0:
               end_LBL_result.setText("The Winner Is Shahar");
                end_IMG_player.setImageResource(R.drawable.img_player_one);
                break;
            case 1:
                end_LBL_result.setText("The Winner Is Ron");
                end_IMG_player.setImageResource(R.drawable.img_player_two);
                break;
            case 2:
                end_LBL_result.setText("Its a Tie !!!");
                end_IMG_player.setImageResource(R.drawable.img_logo);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + score);
        }
    }

    private void closeActivity() {
        finish();
    }
}