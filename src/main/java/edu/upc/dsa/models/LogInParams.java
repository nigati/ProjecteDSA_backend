package edu.upc.dsa.models;

public class LogInParams {
    public String name;
    public String pass;

    public LogInParams() {}
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

    public void setName(String name){
        this.name=name;
    }

    public void setPass(String pass)
    {
        this.pass=pass;
    }
}
