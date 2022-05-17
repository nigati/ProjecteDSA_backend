package edu.upc.dsa.models;

public class LogInParams {
    public String username;
    public String password;

    public LogInParams() {}
    public LogInParams(String name, String pass)
    {
        this.username =name;
        this.password =pass;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
