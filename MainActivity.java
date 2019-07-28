package com.example.tiffanytse.androidhangman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    HangmanUser hangman;
    public static ArrayList<String> phraseList = new ArrayList <String> ( );
    String phrase;
    StringBuilder hiddenPhrase;
    String guess;
    int misses;

    EditText userGuess;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bgdView = (ImageView) findViewById(R.id.bgdImageView);
        bgdView.setImageResource(R.drawable.bgd2);
        TextView welcome = (TextView) findViewById(R.id.welcomeId);
        welcome.setText("WELCOME;)");

        try {
            InputStream stream = getAssets().open("hangPhrases.txt");
            BufferedReader breader = new BufferedReader(new InputStreamReader(stream));
            hangman = new HangmanUser(breader);
            phrase = hangman.randomPhrase();
            hiddenPhrase = hangman.generateHiddenPhrase(phrase);
            TextView tView = (TextView) findViewById(R.id.hidden);
            tView.setText(hiddenPhrase);

        }
        catch (IOException e){
        }

    }

    public void onButtonClick(View view) { //when user hits submit button
        Button b = (Button) findViewById(R.id.submitButton);
        userGuess = (EditText) findViewById(R.id.getGuess);

        if (userGuess.getText().length() > 0) {
            char c = userGuess.getText().charAt(0);
            Boolean match = hangman.processGuess(phrase, hiddenPhrase, c);
            if (match == false) {
                ImageView imgView = (ImageView) findViewById(R.id.imageView4);
                TextView endGame = (TextView) findViewById(R.id.gameOver);
                TextView endGame2 = (TextView) findViewById(R.id.gameOver2);

                misses++;
                for (int i = 1; i < 7; i++) {
                    if (misses == 1) {
                        imgView.setImageResource(R.drawable.hangman2);
                    } else if (misses == 2) {
                        imgView.setImageResource(R.drawable.hangman3);
                    } else if (misses == 3) {
                        imgView.setImageResource(R.drawable.hangman4);
                    } else if (misses == 4) {
                        imgView.setImageResource(R.drawable.hangman5);
                    } else if (misses == 5) {
                        imgView.setImageResource(R.drawable.hangman6);
                    } else if (misses == 6) {
                        imgView.setImageResource(R.drawable.hangman7);
                    } else {
                        endGame.setText("GAME OVER!");
                        endGame2.setText("Pls restart the app to continue guessin'...");


                    }

                }
            } else if (match) {
                TextView tView = (TextView) findViewById(R.id.hidden);
                tView.setText(hiddenPhrase);


            }
        }
    }

}
