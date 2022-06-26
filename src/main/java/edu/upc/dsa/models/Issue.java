package edu.upc.dsa.models;

public class Issue {
    private String id;
    private String message;
    private String date;
    private int user_id;


    public Issue(){

    }
    public Issue(String date, String message){
        this.date = date;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
