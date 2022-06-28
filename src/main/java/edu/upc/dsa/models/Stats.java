package edu.upc.dsa.models;

public class Stats {

    private int id;
    private String username;
    private int time;
    private int enemiesKilled;
    private int level;
    private String avatar;

    public Stats(String username) {
        this.username = username;
        this.time = 0;
        this.avatar = "https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png";
        this.enemiesKilled = 0;
        this.level = 0;
    }

    public Stats(){

    }

    public Stats (String username, int time, String avatar)
    {
        this.username=username;
        this.time =time;
        this.avatar = "https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png";
        this.enemiesKilled=0;
        this.level=0;
    }

    public Stats(String username, int time, int enemiesKilled, int level)
    {
        this.username=username;
        this.level=level;
        this.time=time;
        this.enemiesKilled=enemiesKilled;
        this.avatar = "https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png";
    }

    public int getId() {return id;}

    public void setId(int id){this.id =id;}
    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time =time;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public int getEnemiesKilled()
    {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled)
    {
        this.enemiesKilled=enemiesKilled;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level=level;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public void setAvatar(String avatar){
        this.avatar=avatar;
    }



}
