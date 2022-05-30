package edu.upc.dsa.models;

public class Stats {
    private String username;
    private int points;
    private int enemiesKilled;
    private int level;

    public Stats(String username) {
        this.username = username;
        this.points = 0;
        this.enemiesKilled = 0;
        this.level = 0;
    }
}
