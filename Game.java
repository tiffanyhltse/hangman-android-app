package com.example.tiffanytse.androidhangman;
public abstract class Game implements Comparable {
    private float score;
    private String playerId;


    public void setPlayerId( String pId) {
        this.playerId = pId;
    }

    public abstract float getScore();

    public String getPlayerId() {
        return playerId;
    }

    @Override
    public int compareTo(Object other){ //compare scores -> high scores better
        Game otherGame = (Game) other;

        if (this.getScore() > otherGame.getScore()) {
            return 1;
        } else if (this.getScore() == otherGame.getScore()) {
            return 0;
        }

        return -1;
    }

    public String toString(){
        return "playerId:" + " " + this.playerId + ", " + "score:" + " " + String.valueOf(this.score);
    }
}


