package edu.upc.dsa.models;

public class Game {
    public int id;
    public String username;
    public int hp;
    public int level;
    public int coins;
    public int kills;



    public int time;

    public Game(){}
    public Game(int id, String username, int hp, int level, int coins, int kills, int time ) {
        this.id=id;
        this.username=username;
        this.hp=hp;
        this.level=level;
        this.coins=coins;
        this.kills=kills;
        this.time=time;
    }
    public int getID()
    {
        return id;
    }
    public void setName(int id){
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getHp(){
        return hp;
    }
    public void setHp(int hp){ this.hp=hp;}

    public int getLevel(){
        return level;
    }
    public void setLevel(int level){ this.level=level;}

    public int getCoins(){
        return hp;
    }
    public void setCoins(int hp){ this.hp=hp;}

    public int getKills(){
        return kills;
    }
    public void setKills(int kills){ this.kills=kills;}

    public int getTime() {return time;}
    public void setTime(int time) {this.time = time;}

}
