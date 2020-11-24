package com.example.hw1_ron;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;


    public Deck(ArrayList<Card> deck) {

        this.deck = deck;
    }

    public ArrayList<Card> getDeck() {

        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {

        this.deck = deck;
    }

    public void initDeck(){
        int counter = 0;
        String type ="";
        for (int i = 0; i < 52 ; i++) {
            if( i%13 == 0) counter++;
                switch (counter){
                    case 1:
                        deck.add(new Card(i%13,"club"));
                        break;
                    case 2:
                        deck.add(new Card(i%13,"spade"));
                        break;
                    case 3:
                        deck.add(new Card(i%13,"heart"));
                        break;
                    case 4:
                        deck.add(new Card(i%13,"diamond"));
                        break;
                    default: break;

                }

        }
    }
    public void shuffle(){
        Collections.shuffle(deck);
    }

}
