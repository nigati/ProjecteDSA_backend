package edu.upc.dsa.models;

public class LogInParams {
    String name;
    String pass;

    public LogInParams(String name, String pass)
    {
        this.name=name;
        this.pass=pass;
    }

    public String getName()
    {
        return name;
    }

    public String getPass()
    {
        return pass;
    }
}
