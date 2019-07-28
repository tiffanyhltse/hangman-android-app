package com.example.tiffanytse.androidhangman;


import java.io.BufferedReader;
import java.util.Scanner;

public class HangmanUser extends Hangman {
    //private GamesRecord gr = new GamesRecord( );
    public HangmanUser(BufferedReader br){
        super(br);

    }


    @Override
    public char getGuess(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please guess a letter: ");
            String guess = scanner.nextLine();
            System.out.println("Your guess is: " + guess);
            if (((guess.charAt(0) > 'z') || (guess.charAt(0) < 'a')) &&
                    ((guess.charAt(0) > 'Z') || (guess.charAt(0) < 'A')) &&
                    ((guess.charAt(0) < '0') || (guess.charAt(0) > '9'))) {
                System.out.println("Your guess is invalid! Please enter your guess again: ");
                continue;
            }

            return (guess.charAt(0));
        }
    }



    /*public void play(){ //plays 1 & many games until user enters 'no'
        String myPhrase;
        int  score;

        this.readPhrases();
        myPhrase = this.randomPhrase(phraseList);
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter playerId: ");
        String id = sc.nextLine();
        System.out.println("Current playerId:" + " " + id);

        this.setPlayerId(id);


        do {
            myPhrase = this.randomPhrase();
            score = this.playOneGame(myPhrase);
            gr.add(new HangmanUser(score,id));

            Scanner scanner = new Scanner(System.in);
            System.out.print("Play a game : (yes/no)");

            String ans = scanner.nextLine();

            if (ans.equals("no")) {
                break;
            }

            System.out.println("Please enter playerId: ");
            id = sc.nextLine();
            System.out.println("Current playerId:" + " " + id);

            this.setPlayerId(id);


        } while(true);

        System.out.print("average score of all games: ");
        System.out.println(gr.average());

        Scanner newSc = new Scanner(System.in);
        System.out.println("Please enter which playerId's average score you want to see. ");
        String whichId = newSc.nextLine();
        System.out.print(whichId + "'s " + "average: ");
        System.out.println(gr.average(whichId));

        System.out.println("List of top game scores "); //list lower scores first
        for (Game game : gr.highGameList(2)) {
            System.out.println(game);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HangmanUser hUser = new HangmanUser(0, "HangmanUser");
        hUser.play();

    }*/
}

