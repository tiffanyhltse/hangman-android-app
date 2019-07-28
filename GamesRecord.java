package com.example.tiffanytse.androidhangman;
import java.util.ArrayList;
import java.util.Collections;

public class GamesRecord {

    private ArrayList<Game> gameList = new ArrayList<Game>(); //list of Game


    public void add(Game game){ //adds a Game to the GamesRecord

        gameList.add(game);

    }

    public float average(){ //returns the average score for all games added to the record
        int sum = 0;
        int count = 0;

        for (Game game : gameList) {
            count++;
            sum += game.getScore();
        }

        return (float)sum/ (float)count;

    }

    public float average(String playerId){ //returns the average score for all games of a particular player
        //HangmanUser asks who the playerId is first before playing games
        //if playerId is who I'm looking for, then return the average scores for that player
        int sum = 0;
        int count = 0;

        for (Game game : gameList){

            if (game.getPlayerId().equals(playerId)) {
                count++;
                sum += game.getScore();
            }
        }

        return (float)sum/(float)count;
    }

    public ArrayList<Game> highGameList(int n){ //returns a list of the top n scores including player & score
        Collections.sort(this.gameList);

        return (new ArrayList<Game> (this.gameList.subList(0, n)));

    }



}

