package edu.upc.dsa.models;

public class Issue {
    private String date;
    private String informer;
    private String message;

    public Issue(){

    }
    public Issue(String date, String informer, String message){
        this.date = date;
        this.message = message;
        this.informer = informer;
    }

    public String getDate() {
        return date;
    }

    public String getInformer() {
        return informer;
    }

    public String getMessage() {
        return message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInformer(String informer) {
        this.informer = informer;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
