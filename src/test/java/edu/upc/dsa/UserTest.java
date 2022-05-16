package edu.upc.dsa;

import edu.upc.dsa.models.LogInParams;
import edu.upc.dsa.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    UserManager myUserManager;

    @Before
    public void setUp() throws Exception{
        this.myUserManager = UserManagerImpl.getInstance();
    }

    @After
    public void tearDown() {
        // this.myProductManager.clear();
    }

    @Test
    public void testRegistro() throws Exception {
        User u1= new User("Gilbert", "gilbert@gmail.com", "guapo");
        myUserManager.addUser(u1);
        User u2= new User("Gilbert", "gilbert9@gmail.com", "guapo9");
        myUserManager.addUser(u2);
    }
    @Test
    public void testLogin() throws Exception {
        User u1= new User("Gilbert", "gilbert@gmail.com", "guapo");
        myUserManager.addUser(u1);

        User gilbert1= myUserManager.login("Gilbert","guapo1");
        Assert.assertEquals(null, gilbert1);
        User gilbert= myUserManager.login("Gilbert","guapo");
        Assert.assertEquals("Gilbert", gilbert.getUsername());
        LogInParams login1= new LogInParams("Claudia", "abc");
        User auxClaudia= myUserManager.login(login1.getUsername(),login1.getPassword());
        Assert.assertEquals(null,auxClaudia);

        LogInParams login2= new LogInParams("Gilbert", "guapo");
        User auxGilb= myUserManager.login(login2.getUsername(),login2.getPassword());
        Assert.assertEquals("guapo",auxGilb.getPassword());



    }
}
