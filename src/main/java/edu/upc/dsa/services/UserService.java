package edu.upc.dsa.services;


import edu.upc.dsa.StatsManager;
import edu.upc.dsa.StatsManagerImpl;
import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.models.*;
import edu.upc.dsa.mysql.ItemManagerDAO;
import edu.upc.dsa.mysql.ItemManagerDAOImpl;
import edu.upc.dsa.mysql.UserManagerDAO;
import edu.upc.dsa.mysql.UserManagerDAOImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/users", description = "Endpoint to user Service")
@Path("/users")
public class UserService {

    private UserManager um;
    private UserManagerDAO umd;
    private ItemManagerDAO imd;
    private StatsManager sm;

    public UserService() {
        this.um = UserManagerImpl.getInstance();
        this.umd = UserManagerDAOImpl.getInstance();
        this.imd = ItemManagerDAOImpl.getInstance();
        if(um.getUsers().size()==0){
            um.addUser(new User("admin","admin@admin","admin"));
        }
        this.sm = StatsManagerImpl.getInstance();

    }


    @POST
    @ApiOperation(value = "create a new user", notes = "xd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 404, message = "Username is already taken")

    })

    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {

        if (user.getUsername().equals("") || user.getEmail().equals("") || user.getPassword().equals(""))
        {
            return Response.status(404).entity(user).build();
        }

        User checking = this.um.addUser(user);
        this.umd.addUser(user);
        if (checking != null)
        {
            return Response.status(201).entity(user).build();
        }
        else
        {
            return Response.status(404).entity(user).build();
        }
    }

    @GET
    @ApiOperation(value = "get all Objects in Catalogo", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/catalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObjects() {

        List<Item> items = this.um.catalogoTienda();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build();

    }
    @POST
    @ApiOperation(value="Retrieve issues", notes = "minim2")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful")
    })

    @Path("/issue")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response issues(Issue issue){
        System.out.println("issue date: " + issue.getDate() + "; Informer: " + issue.getInformer() + "; Message: " + issue.getMessage());
        return Response.status(201).build();
    }


    @POST
    @ApiOperation(value = "log in user", notes = "xd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
            @ApiResponse(code = 401, message = "Incorrect username or password")

    })

    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logIn(LogInParams loginpar) {

        //System.out.println("PARAMETROS "+loginpar.getUsername()+" ===> "+loginpar.getPassword());
            User u = this.umd.login(loginpar);
            User u2 = this.um.login(loginpar.getUsername(),loginpar.getPassword());
            if (u2!= null) {
                return Response.status(201).entity(u2).build();
            }
            else {

                //System.out.println("Usuario o contraseña incorrectos");
                return Response.status(401).build();

            }
    }

    @PUT
    @ApiOperation(value = "update a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}")
    public Response updateUser(String username) {

        User user=umd.getUser(username);
        User u = this.umd.updateUser(user.getUsername(),user.getEmail(), user.getPassword());

        if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "buy Item from shop", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Something went wrong")
    })
    @Path("/buyItem")
    public Response buyItem(ToBuyItems toBuyItems) {

        User user=umd.getUser(toBuyItems.getPlayer());
        //System.out.println("Consigo al player: "+ user.getCoins());
        Item i =imd.getItem(toBuyItems.getItem());
        //System.out.println("Consigo al item: "+ i.getCoins());
        User u= this.umd.buyItem(toBuyItems.getItem(),toBuyItems.getPlayer());

        if (u == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "get a User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username")String username) {

        User user=umd.getUser(username);
        if (user == null) return Response.status(404).build();
        return Response.status(201).entity(user).build();

    }





}