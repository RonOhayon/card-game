package com.example.hw1_ron;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class gameActivity extends AppCompatActivity {
    private ImageView game_IMG_card1;
    private ImageView game_IMG_card2;
    private TextView game_LBL_playerOne;
    private TextView game_LBL_playerTwo;
    private ImageButton game_BTN_play;
    private Deck deck;
    private ArrayList<Card> playerOne;
    private ArrayList<Card> playerTwo;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private  int counter = 0;
    final int DELAY =1000;
    private Timer carousalTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        // setup decks
        ArrayList<Card> cards = new ArrayList<Card>();
        playerOne = new ArrayList<Card>();
        playerTwo = new ArrayList<Card>();
        deck = new Deck(cards);

        //set players
        setHands(deck, playerOne, playerTwo);
        initView();
        setBTN();
    }

    public void setHands(Deck deck, ArrayList<Card> playerOne, ArrayList<Card> playerTwo) {
        deck.initDeck();
        deck.shuffle();
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) {
                playerOne.add(deck.getDeck().get(i));
            }
            else {
                playerTwo.add(deck.getDeck().get(i));
            }
        }
    }

    public void initView() {
        game_BTN_play = findViewById(R.id.game_BTN_play);
        game_IMG_card1 = findViewById(R.id.game_IMG_card1);
        game_IMG_card2 = findViewById(R.id.game_IMG_card2);
        game_LBL_playerOne = findViewById(R.id.game_LBL_playerOne);
        game_LBL_playerTwo = findViewById(R.id.game_LBL_playerTwo);

    }

    public void setBTN(){
        game_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startGame(); }
        });
    }

    private void playView(ArrayList<Card> playerOne, ArrayList<Card> playerTwo, int counter) {

        //set player1 image to the card
        int numP1 = playerOne.get(counter).getCardNum();
        String nameP1 = playerOne.get(counter).getType();
        String numNameP1=intToString(numP1);
        int leftImage = getResources().getIdentifier("img_"+nameP1+"_"+numNameP1,"drawable",getPackageName());
        game_IMG_card1.setImageResource(leftImage);

        //set player2 image to the card
        int numP2 = playerTwo.get(counter).getCardNum();
        String nameP2 = playerTwo.get(counter).getType();
        String numNameP2=intToString(numP2);
        int rightImage = getResources().getIdentifier("img_"+nameP2+"_"+numNameP2,"drawable",getPackageName());
        game_IMG_card2.setImageResource(rightImage);


    }

    private String intToString(int num) {
        String name = "";
        switch (num) {
            case 0:
                name="one";
                break;
            case 1:
                name="two";
                break;
            case 2:
                name="three";
                break;
            case 3:
                name="four";
                break;
            case 4:
                name="five";
                break;
            case 5:
                name="six";
                break;
            case 6:
                name="seven";
                break;
            case 7:
                name="eight";
                break;
            case 8:
                name="nine";
                break;
            case 9:
                name="ten";
                break;
            case 10:
                name="eleven";
                break;
            case 11:
                name="twelve";
                break;
            case 12:
                name = "thirteen";
                break;
            default:
                break;
        }
        return name;
    }

    private void openWinView(int winner) {
        int val = (scoreP1>scoreP2 ? scoreP1:scoreP2);
        Intent myIntent = new Intent(this, End_screen.class);
        myIntent.putExtra(End_screen.EXTRA_KEY_WIN,winner);
        myIntent.putExtra(End_screen.EXTRA_KEY_SCORE,val);
        this.startActivity(myIntent);

    }

    private int setWinner(int scoreP1,int scoreP2){
        if (scoreP1>scoreP2) return 0 ;
        else if (scoreP1<scoreP2) return 1;
        else return 2;
    }

    private void closeActivity() {
        finish();
    }

    private int gameSec(ArrayList<Card> playerOne,ArrayList<Card> playerTwo){
        if(counter==5){
            endGame();
        }
        playView(playerOne,playerTwo,counter);
        int numP1 = playerOne.get(counter).getCardNum();
        int numP2 = playerTwo.get(counter).getCardNum();

        // game score
        if(numP1 > numP2) {
            scoreP1++;
            game_LBL_playerOne.setText(""+scoreP1);
        }
        else if(numP1 < numP2){
            scoreP2++;
            game_LBL_playerTwo.setText(""+scoreP2);
        }
       return counter ++;

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopGame();
    }

    private void startGame(){
        carousalTimer = new Timer();
        carousalTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gameSec(playerOne,playerTwo);
                    }
                });
            }
        }, 0, DELAY);

    }

    private void stopGame(){
        carousalTimer.cancel();
    }

    private void endGame(){
            int winner = setWinner(scoreP1, scoreP2);
            carousalTimer.cancel();
            openWinView(winner);
            closeActivity();
    }
}
