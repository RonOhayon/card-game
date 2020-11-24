package com.example.hw1_ron;

import android.widget.ImageView;

public class Card {

    private int cardNum;
    private String type;
    public Card(int cardNum,String type) {
        this.cardNum = cardNum;
        this.type = type;

    }


    public int getCardNum() {
        return cardNum;
    }

    public String getType() { return type; }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "cardNum=" + cardNum + ", type='" + type;
    }
}



