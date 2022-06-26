package edu.upc.dsa.models;

public class ForumMessage {
    int id;
    String username;
    String message;
    Integer numseq;

    public ForumMessage(){}

    public ForumMessage(String username, String message, Integer numseq) {
        this.username = username;
        this.message = message;
        this.numseq = numseq;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public void setNumseq(Integer numSeq) {
        this.numseq = numSeq;
    }

    public Integer getNumseq() {
        return numseq;
    }
}
