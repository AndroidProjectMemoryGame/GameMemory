package com.example.hoih.my.gamememory;

public class Level {

    private int level;
    private int score;

    public Level(int level, int score) {
        this.level = level;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
