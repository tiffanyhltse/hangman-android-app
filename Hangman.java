package com.example.tiffanytse.androidhangman;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;

public abstract class Hangman extends Game {
    private ArrayList <String> phraseList = new ArrayList <String> ( );
    private int phraseIdx = 0;
    private float score;

    //TextView displayHiddenPhrase = (TextView) findViewById(R.id.displayHiddenPhrase);
    //TextView displayGuess = (TextView) findViewById(R.id.displayGuess);
    //TextView displayUpdate = (TextView) findViewById(R.id.displayUpdate);

    public Hangman(BufferedReader br){
        readPhrases(br);

    }


    public abstract char getGuess(); //both subclasses (HangmanUser & HangmanAI) responsible for implementing

    public String randomPhrase(){ //returns a single phrase randomly chosen from a list
        Random random = new Random();
        int x = random.nextInt(phraseList.size()); //any random phrase out of text file

        String anyPhrase = phraseList.get(x); //assign the randomPhrase to a variable named anyPhrase (randomly chosen phrase)

        return anyPhrase;

    }

    public String getNextPhrase( ) {
        if (phraseIdx < this.phraseList.size()) {
            return phraseList.get(phraseIdx++);
        } else {
            return null;
        }
    }

    public StringBuilder generateHiddenPhrase(String anyPhrase){ //returns the initial hidden phrase
        StringBuilder hiddenPhrase = new StringBuilder(anyPhrase); //turn the anyPhrase into a mutable string (hidden phrase)

        int i = 0;
        //char a = '*';
        while (i < anyPhrase.length()) { //loop through the anyPhrase
            if (anyPhrase.charAt(i) != ' ' && // //if the character in the anyPhrase is not a space or punctuation:
                    anyPhrase.charAt(i) != '!' &&
                    anyPhrase.charAt(i) != '.' &&
                    anyPhrase.charAt(i) != '?' &&
                    anyPhrase.charAt(i) != ',') {
                hiddenPhrase.setCharAt(i, '*'); //replace all letters with asterisks
            } else {
                hiddenPhrase.setCharAt(i, anyPhrase.charAt(i)); //else keep the existing character
            }
            i++; //move on to next character

        }
        StringBuilder hidden = new StringBuilder(hiddenPhrase);
        return hidden;

    }

    public boolean processGuess(String anyPhrase, StringBuilder hiddenPhrase, char guess){ //returns whether a letter matches & modifies the partially hidden phrase

        int c = 0; //counter variable
        int hit = 0; //variable to check if there are any asterisks that still need to be guessed
        while (c < hiddenPhrase.length()) { //loop through hidden phrase
            //System.out.println(c);
            //System.out.println(guess.charAt(0));
            //System.out.println(sb.charAt(c));
            if (guess == anyPhrase.charAt(c)) { //if letter that the user guessed exists in the random phrase:
                //System.out.println("enter");
                hit = 1;
                hiddenPhrase.setCharAt(c, guess); //replace the asterisks with the user-guessed letter at the applicable position
            }
            c++;

        }
        //System.out.println(hiddenPhrase);
        //return hiddenPhrase;
        if(hit > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void readPhrases(BufferedReader breader){ //reads phrases from a file into a list

        try {
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = breader.readLine()) != null) {
                this.phraseList.add(line.toLowerCase());
            }
            //System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int playOneGame(String myPhrase) { //plays a single game
        //System.out.println(myPhrase);
        StringBuilder hiddenPhrase = this.generateHiddenPhrase(myPhrase);
        //System.out.println(hiddenPhrase);

        int score = 0;
        boolean g;

        while (true) {
            int c = 0;
            int hit = hiddenPhrase.length();
            g = this.processGuess(myPhrase, hiddenPhrase, this.getGuess()); //process guess
            //System.out.println(g);
            if (!g){ // if(!process guess) guesses wrong
                score--;
            } else {
                score++;
            }

            while (c < hiddenPhrase.length()) {
                if (hiddenPhrase.charAt(c) != '*') { //if a character in the hidden phrase is still an asterisk:
                    hit = hit - 1; //continue game (asterisks still need to be replaced)
                }
                c++;
            }
            //System.out.println(hit + " " + "asterisk(s) still need to be guessed");

            if (hit == 0) { //if no remaining asterisks that have to be guessed exist, user wins
                return score;
            }
        }

    }

    public float getScore(){
        return score;
    }


    @Override
    public int compareTo(Object other){ //compare scores -> low scores better
        Game otherGame = (Game) other;

        if (this.getScore() > otherGame.getScore()) {
            return -1;
        } else if (this.getScore() == otherGame.getScore()) {
            return 0;
        }

        return 1;
    }

}

